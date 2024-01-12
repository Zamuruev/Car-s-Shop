package CarShop.utils.validation.user;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserLoginValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface UniqueUserLogin {
    String message() default "A user with this login already exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
