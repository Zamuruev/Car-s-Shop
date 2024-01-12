package CarShop.utils.validation.brand;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UniqueBrandNameValidator.class)
public @interface UniqueBrandName {
    String message() default "The brand already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
