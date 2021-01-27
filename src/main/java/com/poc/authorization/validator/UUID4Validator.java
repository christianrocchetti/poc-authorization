package com.poc.authorization.validator;

import com.poc.authorization.validator.annotation.UUID4;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Pattern;

public class UUID4Validator implements ConstraintValidator<UUID4, String> {

    @Override
    public void initialize(UUID4 constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !Objects.isNull(value) && Pattern.matches("(?i)^[0-9A-F]{8}-[0-9A-F]{4}" +
                "-[4][0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}$", value);
    }
}
