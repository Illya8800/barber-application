package com.barber.hopak.exception.entity.haircut.order;

import jakarta.persistence.EntityNotFoundException;

public class HaircutOrderNotFoundException extends EntityNotFoundException {
    public HaircutOrderNotFoundException(String s) {
        super(s);
    }
}
