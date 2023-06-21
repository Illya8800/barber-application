package com.barber.hopak.constrain.payment;

import com.barber.hopak.exception.RankNotFoundException;
import com.barber.hopak.model.enumeration.PaymentType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PaymentTypeNameValidator  implements ConstraintValidator<PaymentTypeName, PaymentType> {
    @Override
    public boolean isValid(PaymentType value, ConstraintValidatorContext context) {
        try {
            return PaymentType.isValidType(value);
        } catch (RankNotFoundException e) {
            return false;
        }
    }
}
