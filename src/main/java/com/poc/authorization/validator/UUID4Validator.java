package com.poc.authorization.validator;

import com.poc.authorization.validator.annotation.IsUUID4;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UUID4Validator implements ConstraintValidator<IsUUID4, String> {


    @Override
    public void initialize(IsUUID4 constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.matches("(?i)^[0-9A-F]{8}-[0-9A-F]{4}-[4][0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}$", value);
    }
}
