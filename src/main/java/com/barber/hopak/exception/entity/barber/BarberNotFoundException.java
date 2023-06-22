package com.barber.hopak.exception.entity.barber;

import jakarta.persistence.EntityNotFoundException;

public class BarberNotFoundException  extends EntityNotFoundException {
    public BarberNotFoundException(String s) {
        super(s);
    }
}