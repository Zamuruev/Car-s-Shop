package CarShop.services.Interf;

import CarShop.models.dtos.BrandDTO;

import java.util.List;

public interface BrandService {
    BrandDTO addBrand(BrandDTO brandDTO);
    void deleteBrand(String id);
    void deleteAllBrands();
    List<BrandDTO> allBrands();
    BrandDTO getBrandById(String id);
    BrandDTO getBrandByName(String name);
    void updateTop(String id);
    List<BrandDTO> findTop3ByOOrderByTopTopDesc();
    void update(String name, String id);
}
