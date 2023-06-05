package com.barber.hopak.constrain.image;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.barber.hopak.constrain.message.DtoConstraintMessage.IMAGE_TYPE_UNKNOWN;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageExtensionsNameValidator.class)
public @interface ImageExtensionsName {
    String message() default IMAGE_TYPE_UNKNOWN;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
