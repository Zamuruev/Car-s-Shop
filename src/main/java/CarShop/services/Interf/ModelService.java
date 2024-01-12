package CarShop.services.Interf;

import CarShop.models.dtos.ModelDTO;

import java.util.List;

public interface ModelService {
    void addModel(ModelDTO modelDTO);
    void deleteModel(String id);
    void deleteAllModels();
    List<ModelDTO> allModelsNotBasket();
    ModelDTO getModelById(String id);
    void modelActive(String id, String login);
    void deleteAllModelsByActive();
}
