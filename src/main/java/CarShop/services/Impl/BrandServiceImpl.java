package CarShop.services.Impl;

import CarShop.models.dtos.BrandDTO;
import CarShop.models.entities.Brand;
import CarShop.repositories.BrandRepository;
import CarShop.services.Interf.BrandService;
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
public class  BrandServiceImpl implements BrandService {

    private final ModelMapper modelMapper;
    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @CacheEvict(cacheNames = "brands", allEntries = true)
    @Override
    public BrandDTO addBrand(BrandDTO brandDTO) {
        Brand newBrand = modelMapper.map(brandDTO, Brand.class);
        newBrand.setCreated(LocalDateTime.now());
        newBrand.setModified(LocalDateTime.now());
        return modelMapper.map(brandRepository.save(newBrand), BrandDTO.class);
    }

    @CacheEvict(cacheNames = "brands", allEntries = true)
    @Override
    public void deleteBrand(String id) {
        brandRepository.deleteById(id);
    }

    @CacheEvict(cacheNames = "brands", allEntries = true)
    @Override
    public void deleteAllBrands() {
        brandRepository.deleteAll();
    }

    @Cacheable("brands")
    @Override
    public List<BrandDTO> allBrands() {
        return brandRepository.findAll().stream()
                .map(brand -> modelMapper.map(brand, BrandDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BrandDTO getBrandById(String id) {
        return modelMapper.map(brandRepository.findById(id), BrandDTO.class);
    }

    @Override
    public BrandDTO getBrandByName(String name) {
        return modelMapper.map(brandRepository.findBrandByName(name), BrandDTO.class);
    }

    @CacheEvict(cacheNames = "brands", allEntries = true)
    @Override
    public void updateTop(String id) {
        BrandDTO updateTopBrand = getBrandById(id);
        int top = updateTopBrand.getTop() + 1;
        brandRepository.updateByTop(top,id);
    }

    @Override
    public List<BrandDTO> findTop3ByOOrderByTopTopDesc() {
        return brandRepository.findTop3ByOrderByTopDesc().stream().map((brand) -> modelMapper.map(brand, BrandDTO.class)).collect(Collectors.toList());
    }

    @CacheEvict(cacheNames = "brands", allEntries = true)
    @Override
    public void update(String name, String id) {
       brandRepository.updateBrand(name, LocalDateTime.now(), id);
    }

}
