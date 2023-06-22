package com.barber.hopak.service;

import java.util.List;

public interface BarberService<Entity, ID> extends BaseService<Entity, ID> {
    List<Entity> findAll();
    void delete(Entity barberDto);
}
