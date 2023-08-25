package com.barber.hopak.exception.entity.haircut;

import jakarta.persistence.EntityNotFoundException;

public class HaircutNotFoundException extends EntityNotFoundException {
    public HaircutNotFoundException(String s) {
        super(s);
    }
}
