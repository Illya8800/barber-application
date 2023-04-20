package com.barber.hopak.service;

import com.barber.hopak.web.domain.impl.ImageDto;

import java.util.List;

public interface ImageService<Entity, ID> {
    Entity findById(ID id);
    Entity findByImageName(String imageName);
    List<Entity> findAllImages();
    Entity createImage(Entity image);
    Entity updateImage(Entity image);
    void deleteById(ID id);
    void delete(Entity imageDto);
    boolean isUnique(Entity imageDto);
    void setImageNameByOriginalFileName(ImageDto imageDto);
}