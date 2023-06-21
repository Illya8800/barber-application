package com.barber.hopak.exception.client;

import jakarta.persistence.EntityNotFoundException;

public class ClientNotFoundException extends EntityNotFoundException {
    public ClientNotFoundException(String s) {
        super(s);
    }
}
