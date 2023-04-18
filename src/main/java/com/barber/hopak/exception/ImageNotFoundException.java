package com.barber.hopak.exception;

import jakarta.persistence.EntityNotFoundException;

public class ImageNotFoundException extends EntityNotFoundException {
    public ImageNotFoundException(String s) {
        super(s);
    }
}
