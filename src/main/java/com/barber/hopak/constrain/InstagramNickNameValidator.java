package com.barber.hopak.constrain;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class InstagramNickNameValidator implements ConstraintValidator<InstagramNickName, String> {
    private static final String instagramPattern = "^(?!.*\\.\\.)(?!.*\\.$)\\w[\\w.]{0,29}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String instagram = value.trim();
        if (instagram.isEmpty()) return true;
        if (!isLengthCorrect(value)) {
            return true;
        }
        return value.matches(instagramPattern);
    }

    private static boolean isLengthCorrect(String value) {
        int minLength = 3;
        int maxLength = 30;
        return StringUtils.isEmpty(value)
                && value.length() > minLength
                && value.length() < maxLength;
    }
}
