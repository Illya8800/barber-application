package com.barber.hopak.constrain.image;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.barber.hopak.constrain.DtoConstraintMessage.IMAGE_FILE_SIZE_SHOULD_BE_BETWEEN_1KB_AND_16MB;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileSizeValidator.class)
public @interface FileSize {
    int minBytes() default 1;

    int maxBytes() default 16777215;

    String message() default IMAGE_FILE_SIZE_SHOULD_BE_BETWEEN_1KB_AND_16MB;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
