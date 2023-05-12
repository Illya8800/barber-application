package com.barber.hopak.constrain;

import com.barber.hopak.exception.image.ImageNotFoundException;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.web.domain.impl.ImageDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueImageNameValidator implements ConstraintValidator<UniqueImageName, ImageDto> {
    private final ImageService<ImageDto, Long> imageService;

    @Override
    public boolean isValid(ImageDto value, ConstraintValidatorContext context) {
        try {
            ImageDto imageDtoByDb = imageService.findByName(value.getImage().getOriginalFilename());
            return imageDtoByDb.getId().equals(value.getId());
        } catch (ImageNotFoundException e) {
            return true;
        }
    }
}