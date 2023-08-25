package com.barber.hopak.service;

import java.util.List;

public interface HaircutService<Entity, ID> extends BaseService<Entity, ID> {
    List<Entity> findAll();
    Entity findByName(String name);
    void delete(Entity haircut);
    boolean isUnique(ID id, String name);
}
