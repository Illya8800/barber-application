package com.barber.hopak.repository;

import com.barber.hopak.model.impl.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    Optional<Image> findById(Long id);

    Optional<Image> findByImageName(String imageName);

    <S extends Image> S save(S image);

    void deleteById(Long id);
}
