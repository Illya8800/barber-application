package com.barber.hopak.constrain;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BarberRankNameValidator.class)
public @interface BarberRankName {
    String message() default "This barber rank isn't exist";
}
