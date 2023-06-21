package com.barber.hopak.service;

import java.util.List;

public interface HaircutService<Entity, ID> extends BaseService<Entity, ID> {
    List<Entity> findAll();
    Entity findByName(String name);
    boolean isUnique(ID id, String name);
}
