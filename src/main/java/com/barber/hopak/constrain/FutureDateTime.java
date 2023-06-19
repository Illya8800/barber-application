package com.barber.hopak.constrain;

import jakarta.validation.Payload;

public @interface FutureDateTime {
    String message() default "Date should be in the future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
