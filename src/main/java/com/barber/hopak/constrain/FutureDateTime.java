package com.barber.hopak.constrain;

public @interface FutureDateTime {
    String message() default "Date should be in the future";
}
