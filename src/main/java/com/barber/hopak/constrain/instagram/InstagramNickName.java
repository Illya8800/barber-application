package com.barber.hopak.constrain.instagram;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InstagramNickNameValidator.class)
public @interface InstagramNickName {
    String message() default "Instagram nickname is not correct";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
