package com.barber.hopak.constrain;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private static final String phoneNumberPattern = "^\\+\\([0-9]{2}\\)[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) return false;
        return value.matches(phoneNumberPattern);
    }
}
