package com.barber.hopak.constrain;

import com.barber.hopak.model.enumeration.BarberRank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BarberRankNameValidator implements ConstraintValidator<BarberRankName, BarberRank> {
    @Override
    public boolean isValid(BarberRank value, ConstraintValidatorContext context) {
        return BarberRank.isValidRank(value);
    }
}
