package com.barber.hopak.service;

import com.barber.hopak.web.domain.impl.ImageDto;

import java.util.List;

public interface ImageService<Entity, ID> {
    Entity findById(ID id);

    Entity findByName(String imageName);

    List<Entity> findAllImages();

    Entity create(Entity image);

    Entity update(Entity image);

    void deleteById(ID id);

    boolean isUnique(ID id, String name);

    void setImageNameByOriginalFileName(ImageDto imageDto);

    boolean isExtensionValid(String fileName);
}