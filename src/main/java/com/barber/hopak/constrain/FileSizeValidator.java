package com.barber.hopak.constrain;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import static com.barber.hopak.util.ImageUtil.MAX_IMAGE_SIZE;

@RequiredArgsConstructor
public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value.getSize() <= MAX_IMAGE_SIZE;
    }
}
