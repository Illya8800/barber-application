package com.barber.hopak.service.buffer;

import java.util.Optional;

public interface BufferService<T> {
    void saveImage(T imageDto);
    Optional<T> findImageById(Long id);
    Optional<T> findImageByName(String name);
    void deleteImageByName(String imageName);
    void deleteImageById(Long id);
}
