package com.barber.hopak.constrain.payment;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DiscountInPercentValidator implements ConstraintValidator<DiscountInPercent, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= 0 && value <= 100;
    }
}
