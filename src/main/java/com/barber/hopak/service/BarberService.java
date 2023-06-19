package com.barber.hopak.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BarberService<Entity, ID> extends BaseService<Entity, ID> {
    List<Entity> findAll();
}
