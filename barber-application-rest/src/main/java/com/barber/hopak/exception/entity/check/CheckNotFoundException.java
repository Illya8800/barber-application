package com.barber.hopak.exception.entity.check;

import jakarta.persistence.EntityNotFoundException;

public class CheckNotFoundException extends EntityNotFoundException {
    public CheckNotFoundException(String s) {
        super(s);
    }
}
