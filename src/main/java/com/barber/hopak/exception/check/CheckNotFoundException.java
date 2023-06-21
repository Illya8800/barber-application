package com.barber.hopak.exception.check;

import jakarta.persistence.EntityNotFoundException;

public class CheckNotFoundException extends EntityNotFoundException {
    public CheckNotFoundException(String s) {
        super(s);
    }
}
