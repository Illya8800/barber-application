package com.barber.hopak.constrain;

import com.barber.hopak.model.Image;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, Image> {
    @Override
    public boolean isValid(Image value, ConstraintValidatorContext context) {
        return false;
    }
}
