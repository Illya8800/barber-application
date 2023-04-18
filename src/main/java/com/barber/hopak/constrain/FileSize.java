package com.barber.hopak.constrain;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileSizeValidator.class)
public @interface FileSize {
    int minBytes() default 1;

    int maxBytes() default 16777215;

    String message() default "This file hadn't a good size";

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
