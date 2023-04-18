package com.barber.hopak.service.impl;

import com.barber.hopak.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class ImageDtoServiceImplTest {

    @Autowired
    private final ImageService imageService;

    @Test
    void findById() {
    }
    @Test
    void findImageByImageName() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }

    @Test
    void isUnique() {
    }
}