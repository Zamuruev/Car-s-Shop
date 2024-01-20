package CarShop.services.Impl;

import CarShop.models.dtos.ModelDTO;
import CarShop.models.entities.Brand;
import CarShop.models.entities.Model;
import CarShop.models.entities.User;
import CarShop.models.enums.Category;
import CarShop.models.enums.Engine;
import CarShop.models.enums.Transmission;
import CarShop.repositories.BasketRepository;
import CarShop.repositories.BrandRepository;
import CarShop.repositories.ModelRepository;
import CarShop.repositories.UserRepository;
import CarShop.services.Interf.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableCaching
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

    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public void addModel(ModelDTO modelDTO) {
        System.out.println(modelDTO);
        Model newModel = modelMapper.map(modelDTO, Model.class);
        newModel.setCreated(LocalDateTime.now());
        newModel.setModified(LocalDateTime.now());
        System.out.println(newModel);
        modelRepository.save(newModel);
    }

    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public void deleteModel(String id) {
        modelRepository.deleteById(id);
    }

    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public void deleteAllModels() {
        modelRepository.deleteAll();
    }

    @Cacheable("models")
    @Override
    public List<ModelDTO> allModelsNotBasket() {
        return modelRepository.findAllByActive("In Basket").stream().map((model) -> modelMapper.map(model, ModelDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ModelDTO getModelById(String id) {
        return modelMapper.map(modelRepository.findById(id), ModelDTO.class);
    }

    @CacheEvict(cacheNames = "models", allEntries = true)
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

    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public void deleteAllModelsByActive() {
        modelRepository.deleteAllByActive("In Basket");
    }

    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public void updateModel(ModelDTO modelDTO) {
        Brand brand = brandRepository.findBrandByName(modelDTO.brand.getName()).orElse(null);
        modelRepository.updateModel(modelDTO.id, LocalDateTime.now(), brand, modelDTO.name, modelDTO.category, modelDTO.transmission, modelDTO.engine, modelDTO.price);
    }
}
