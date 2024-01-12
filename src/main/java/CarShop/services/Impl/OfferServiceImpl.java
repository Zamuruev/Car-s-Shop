package CarShop.services.Impl;

import CarShop.models.dtos.ModelDTO;
import CarShop.models.dtos.OfferDTO;
import CarShop.repositories.BrandRepository;
import CarShop.repositories.ModelRepository;
import CarShop.repositories.OfferRepository;
import CarShop.repositories.UserRepository;
import CarShop.services.Interf.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private ModelRepository modelRepository;
    private BrandRepository brandRepository;
    private UserRepository userRepository;
    private OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper) {
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
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<OfferDTO> offers(String login) {
        return offerRepository.findAllBySellerId(
                userRepository.findByLogin(login).get().getId()
        ).stream().map((offer) -> modelMapper.map(offer, OfferDTO.class)).toList();
    }

    @Override
    public List<ModelDTO> modelsInOffer(String offer_id) {
        return modelRepository.findAllByOfferId(offer_id).stream().map((model) -> modelMapper.map(model, ModelDTO.class)).toList();
    }

}
