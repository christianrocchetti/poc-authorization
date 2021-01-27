package com.poc.authorization.validator.annotation;

import com.poc.authorization.validator.UUID4Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UUID4Validator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UUID4 {
    String message() default "Invalid UUID4";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
