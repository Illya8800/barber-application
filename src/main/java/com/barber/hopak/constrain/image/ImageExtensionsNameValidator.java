package com.barber.hopak.constrain.image;

import com.barber.hopak.service.ImageService;
import com.barber.hopak.web.domain.impl.ImageDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
public class ImageExtensionsNameValidator implements ConstraintValidator<ImageExtensionsName, MultipartFile> {
    private final ImageService<ImageDto, Long> imageService;

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return imageService.isExtensionValid(value.getOriginalFilename());
    }
}
