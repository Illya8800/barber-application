package com.barber.hopak.constrain.instagram;

import com.barber.hopak.service.api.InstagramApiService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InstagramNickNameValidator implements ConstraintValidator<InstagramNickName, String> {
    private final InstagramApiService instagramApiService;
    private static final String INSTAGRAM_VALIDITY_PATTERN = "^(?!.*\\.\\.)(?!.*\\.$)\\w[\\w.]{0,29}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.trim().isEmpty() || !value.matches(INSTAGRAM_VALIDITY_PATTERN)) return false;
        return instagramApiService.isUserExist(value);
    }

}