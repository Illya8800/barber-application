package com.barber.hopak.service;

public interface BaseService <Entity, ID>{
    Entity findById(ID id);
    Entity create(Entity entity);
    Entity update(Entity entity);
    void deleteById(ID id);

}
