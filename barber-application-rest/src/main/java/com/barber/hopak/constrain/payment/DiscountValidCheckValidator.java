package com.barber.hopak.constrain.payment;

import com.barber.hopak.web.domain.impl.PaymentDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DiscountValidCheckValidator implements ConstraintValidator<DiscountValidCheck, PaymentDto> {
    @Override
    public boolean isValid(PaymentDto value, ConstraintValidatorContext context) {
        Integer finalPrice = value.getFinalPrice();
        Integer price = value.getPrice();
        Integer discount = value.getDiscount();
        return finalPrice.equals(price - discount);
    }
}
