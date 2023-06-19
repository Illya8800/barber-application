package com.barber.hopak.constrain;


import jakarta.validation.Payload;

public @interface PaymentTypeName {
    String message() default "This barber rank isn't exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
