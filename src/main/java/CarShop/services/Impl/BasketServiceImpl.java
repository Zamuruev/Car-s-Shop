package CarShop.services.Impl;


import CarShop.models.dtos.ModelDTO;
import CarShop.models.entities.Offer;
import CarShop.models.entities.User;
import CarShop.repositories.BasketRepository;
import CarShop.repositories.ModelRepository;
import CarShop.repositories.OfferRepository;
import CarShop.repositories.UserRepository;
import CarShop.services.Interf.BasketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    private BasketRepository basketRepository;
    private ModelRepository modelRepository;
    private UserRepository userRepository;
    private OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BasketServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setBasketRepository(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<ModelDTO> modelsInBasket(String login) {
        User user = userRepository.findByLogin(login).orElse(null);
        return modelRepository.findAllByBasketId(basketRepository.findByUserId(user.getId()).getId()).stream().map((model) -> modelMapper.map(model, ModelDTO.class)).toList();
    }

    @Override
    public void buy(String login) {
        User user = userRepository.findByLogin(login).orElse(null);
        modelRepository.updateAllByBasketId(
                LocalDateTime.now(),
                offerRepository.save(
                        new Offer("/img/offer.png", user, LocalDateTime.now(), LocalDateTime.now())
                ),
                basketRepository.findByUserId(user.getId()).getId()
        );
    }

}
