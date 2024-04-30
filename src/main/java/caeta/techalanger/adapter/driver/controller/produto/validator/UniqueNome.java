package caeta.techalanger.adapter.driver.controller.produto.validator;


import caeta.techalanger.adapter.driver.controller.cliente.validator.UniqueCPFValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueNomeValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueNome {

    String message() default "Esse nome ja existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
