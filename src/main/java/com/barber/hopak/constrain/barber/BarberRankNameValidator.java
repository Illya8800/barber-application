package com.barber.hopak.constrain.barber;

import com.barber.hopak.exception.RankNotFoundException;
import com.barber.hopak.model.enumeration.BarberRank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BarberRankNameValidator implements ConstraintValidator<BarberRankName, BarberRank> {
    @Override
    public boolean isValid(BarberRank value, ConstraintValidatorContext context) {
        try {
            return BarberRank.isValidRank(value);
        } catch (RankNotFoundException e){
            return false;
        }
    }
}
