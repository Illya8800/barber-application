package com.barber.hopak.constrain;

import com.barber.hopak.model.enumeration.ImageExtensions;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ImageExtensionsNameValidator implements ConstraintValidator<ImageExtensionsName, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        Optional<Map.Entry<String, String>> result = ImageExtensions.getExtensions().entrySet()
                .stream()
                .filter(entry -> Objects.requireNonNull(value.getOriginalFilename()).endsWith(entry.getValue()))
                .findFirst();
        return result.isPresent();
    }
}
