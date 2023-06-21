package com.barber.hopak.constrain.haircut;

import com.barber.hopak.exception.haircut.HaircutNotFoundException;
import com.barber.hopak.service.HaircutService;
import com.barber.hopak.web.domain.impl.HaircutDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueHaircutNameValidator implements ConstraintValidator<UniqueHaircutName, HaircutDto> {
    private final HaircutService<HaircutDto, Long> haircutService;
    @Override
    public boolean isValid(HaircutDto value, ConstraintValidatorContext context) {
        try {
            HaircutDto haircutDtoByDb = haircutService.findByName(value.getName());
            return haircutDtoByDb.getId().equals(value.getId());
        } catch (HaircutNotFoundException e) {
            return true;
        }
    }
}
