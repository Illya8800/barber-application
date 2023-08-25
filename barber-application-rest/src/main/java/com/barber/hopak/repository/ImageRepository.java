package com.barber.hopak.repository;

import com.barber.hopak.model.impl.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    Optional<Image> findByName(String imageName);
}
