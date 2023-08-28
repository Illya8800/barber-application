package com.barber.hopak.constrain.image;

import com.barber.hopak.exception.entity.image.ImageNotFoundException;
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
            if (value.getImage() == null) return false;
            String originalFilename = value.getImage().getOriginalFilename();
            String imageName = originalFilename == null ? "" : originalFilename.trim();
            ImageDto imageDtoByDb = imageService.findByName(imageName);
            return imageDtoByDb.getId().equals(value.getId());
        } catch (ImageNotFoundException e) {
            return true;
        }
    }
}