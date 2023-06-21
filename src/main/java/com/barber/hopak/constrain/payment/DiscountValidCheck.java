package com.barber.hopak.constrain.payment;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.barber.hopak.constrain.DtoConstraintMessage.CALCULATION_DOES_NOT_CONVERGE;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DiscountValidCheckValidator.class)
public @interface DiscountValidCheck {
    String message() default CALCULATION_DOES_NOT_CONVERGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};}
