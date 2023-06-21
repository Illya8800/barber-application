package com.barber.hopak.constrain;

import jakarta.validation.Payload;

import static com.barber.hopak.constrain.DtoConstraintMessage.ROLE_NAME_IS_UNKNOWN;

public @interface RoleEnumName {
    String message() default ROLE_NAME_IS_UNKNOWN;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
