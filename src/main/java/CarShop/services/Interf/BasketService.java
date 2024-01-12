package CarShop.services.Interf;

import CarShop.models.dtos.ModelDTO;

import java.util.List;

public interface BasketService {
    List<ModelDTO> modelsInBasket(String login);
    void buy(String login);
}
