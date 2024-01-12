package CarShop.services.Impl;

import CarShop.models.dtos.ModelDTO;
import CarShop.models.entities.Model;
import CarShop.models.entities.User;
import CarShop.repositories.BasketRepository;
import CarShop.repositories.BrandRepository;
import CarShop.repositories.ModelRepository;
import CarShop.repositories.UserRepository;
import CarShop.services.Interf.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelMapper modelMapper;
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;
    private BasketRepository basketRepository;
    private UserRepository userRepository;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Autowired
    public void setBasketRepository(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addModel(ModelDTO modelDTO) {
        System.out.println(modelDTO);
        Model newModel = modelMapper.map(modelDTO, Model.class);
        newModel.setCreated(LocalDateTime.now());
        newModel.setModified(LocalDateTime.now());
        System.out.println(newModel);
        modelRepository.save(newModel);
    }

    @Override
    public void deleteModel(String id) {
        modelRepository.deleteById(id);
    }

    @Override
    public void deleteAllModels() {
        modelRepository.deleteAll();
    }

    @Override
    public List<ModelDTO> allModelsNotBasket() {
        return modelRepository.findAllByActive("In Basket").stream().map((model) -> modelMapper.map(model, ModelDTO.class)).toList();
    }

    @Override
    public ModelDTO getModelById(String id) {
        return modelMapper.map(modelRepository.findById(id), ModelDTO.class);
    }

    @Override
    public void modelActive(String id, String login) {
        Model oldModel = modelRepository.findById(id).orElse(null);
        if(oldModel.getActive().equals("In Basket")) {
            User user = userRepository.findByLogin(login).orElse(null);
            modelRepository.updateActiveById("Added", id, LocalDateTime.now(),basketRepository.findByUserId(user.getId()));
        }
        else {
            modelRepository.updateActiveById("In Basket", id, LocalDateTime.now(), null);
        }
    }

    @Override
    public void deleteAllModelsByActive() {
        modelRepository.deleteAllByActive("In Basket");
    }
}
