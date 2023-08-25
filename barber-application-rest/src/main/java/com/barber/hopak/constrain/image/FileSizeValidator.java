package com.barber.hopak.constrain.image;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
    private int maxBytes;
    private int minBytes;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        maxBytes = constraintAnnotation.maxBytes();
        minBytes = constraintAnnotation.minBytes();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.getSize() >= minBytes && value.getSize() <= maxBytes;
    }
}
