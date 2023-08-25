package com.barber.hopak.constrain.haircut;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.barber.hopak.constrain.DtoConstraintMessage.HAIRCUT_NAME_SHOULD_BE_UNIQUE;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueHaircutNameValidator.class)
public @interface UniqueHaircutName {
    String message() default HAIRCUT_NAME_SHOULD_BE_UNIQUE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
