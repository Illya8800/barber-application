package com.barber.hopak.constrain.image;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.barber.hopak.constrain.DtoConstraintMessage.IMAGE_NAME_SHOULD_BE_UNIQUE;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueImageNameValidator.class)
public @interface UniqueImageName {
    String message() default IMAGE_NAME_SHOULD_BE_UNIQUE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
