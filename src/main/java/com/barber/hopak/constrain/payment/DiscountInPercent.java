package com.barber.hopak.constrain.payment;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DiscountInPercentValidator.class)
public @interface DiscountInPercent {
    String message() default "This discount isn't correct";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};}
