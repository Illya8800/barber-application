package com.barber.hopak.constrain;

import jakarta.validation.Payload;

public @interface RoleEnumName {
    String message() default "Undetected user's role";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
