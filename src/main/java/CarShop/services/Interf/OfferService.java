package CarShop.services.Interf;

import CarShop.models.dtos.ModelDTO;
import CarShop.models.dtos.OfferDTO;

import java.util.List;

public interface OfferService {
    List<OfferDTO> offers(String login);
    List<ModelDTO> modelsInOffer(String offer_id);
}
