package com.barber.hopak.constrain.image;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.barber.hopak.constrain.DtoConstraintMessage.IMAGE_ORIGINAL_FILE_NAME_SHOULD_BE_CORRECT;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OriginalFileNameNotBlankValidator.class)
public @interface OriginalFileNameNotBlank {
    String message() default IMAGE_ORIGINAL_FILE_NAME_SHOULD_BE_CORRECT;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
