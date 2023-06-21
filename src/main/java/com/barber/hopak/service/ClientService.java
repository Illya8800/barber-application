package com.barber.hopak.service;

import java.util.List;

public interface ClientService<Entity, ID> extends BaseService <Entity, ID> {
    List<Entity> findAll();
    Entity findByPhoneNumber(String phoneNumber);
    boolean isUnique(ID id, String phoneNumber);
}
