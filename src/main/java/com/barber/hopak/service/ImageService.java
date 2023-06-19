package com.barber.hopak.service;

import java.util.List;

public interface ImageService<Entity, ID> extends BaseService<Entity, ID> {
    Entity findByName(String imageName);
    List<Entity> findAll();
    boolean isUnique(ID id, String name);
    void setImageNameByOriginalFileName(Entity imageDto);
    boolean isExtensionValid(String fileName);
}