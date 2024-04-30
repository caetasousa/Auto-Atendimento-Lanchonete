package caeta.techalanger.adapter.driver.controller.cliente.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCPFValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCPF {

    String message() default "Esse cpf ja existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
