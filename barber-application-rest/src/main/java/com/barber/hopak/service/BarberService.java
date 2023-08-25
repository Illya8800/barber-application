package com.barber.hopak.service;

import java.util.List;

public interface BarberService<E, I> extends BaseService<E, I> {
    List<E> findAll();
    void delete(E barberDto);
}
