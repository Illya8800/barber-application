package com.barber.hopak.service;

import java.util.List;

public interface PaymentService<Entity, ID> extends BaseService<Entity, ID> {
    List<Entity> findAll();
    void deleteById(Long id);
}
