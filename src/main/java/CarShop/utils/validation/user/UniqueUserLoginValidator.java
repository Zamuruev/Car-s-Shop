package CarShop.utils.validation.user;

import CarShop.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class UniqueUserLoginValidator implements ConstraintValidator<UniqueUserLogin, String> {
    private UserRepository userRepository;
    @Autowired
    public UniqueUserLoginValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByLogin(login).isEmpty();
    }
}


