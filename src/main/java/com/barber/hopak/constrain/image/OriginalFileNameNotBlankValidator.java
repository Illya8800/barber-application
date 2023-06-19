package com.barber.hopak.constrain.image;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import static com.barber.hopak.model.enumeration.ImageExtensions.largesLengthExtensionLengthWithDot;

public class OriginalFileNameNotBlankValidator implements ConstraintValidator<OriginalFileNameNotBlank, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        String originalFilename = value.getOriginalFilename();
        return originalFilename != null && originalFilename.trim().length() > largesLengthExtensionLengthWithDot();
    }
}
