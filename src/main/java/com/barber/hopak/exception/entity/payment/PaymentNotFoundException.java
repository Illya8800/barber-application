package com.barber.hopak.exception.entity.payment;

import jakarta.persistence.EntityNotFoundException;

public class PaymentNotFoundException extends EntityNotFoundException {
    public PaymentNotFoundException(String s) {
        super(s);
    }
}
