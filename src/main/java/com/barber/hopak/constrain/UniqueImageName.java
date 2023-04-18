package com.barber.hopak.constrain;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueImageNameValidator.class)
public @interface UniqueImageName {
    String message() default "This image name isn't unique";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
