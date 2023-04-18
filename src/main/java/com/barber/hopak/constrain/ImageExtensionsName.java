package com.barber.hopak.constrain;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageExtensionsNameValidator.class)
public @interface ImageExtensionsName {
    String message() default "This extension isn't support";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
