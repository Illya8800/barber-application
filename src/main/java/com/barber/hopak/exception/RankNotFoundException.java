package com.barber.hopak.exception;

import jakarta.persistence.EntityNotFoundException;

public class RankNotFoundException extends EntityNotFoundException {
    public RankNotFoundException(String s) {
        super(s);
    }
}
