package CarShop.utils.validation.brand;

import CarShop.repositories.BrandRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueBrandNameValidator implements ConstraintValidator<UniqueBrandName, String> {

    private BrandRepository brandRepository;

    @Autowired
    public UniqueBrandNameValidator(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return brandRepository.findBrandByName(name).isEmpty();
    }

}
