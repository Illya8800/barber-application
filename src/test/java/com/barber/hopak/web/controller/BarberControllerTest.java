package com.barber.hopak.web.controller;

import com.barber.hopak.service.BarberService;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.web.domain.impl.BarberDto;
import com.barber.hopak.web.domain.impl.ImageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BarberControllerTest {
    private final MockMvc mockMvc;
    private final BarberService<BarberDto, Long> barberService;
    private final ImageService<ImageDto, Long> imageService;
    @Autowired
    BarberControllerTest(MockMvc mockMvc, BarberService<BarberDto, Long> barberService, ImageService<ImageDto, Long> imageService) {
        this.mockMvc = mockMvc;
        this.barberService = barberService;
        this.imageService = imageService;
    }

    @Test
    void findBarberById() {
    }

    @Test
    void findAllBarbers() {
    }

    @Test
    void createBarber() {
    }

    @Test
    void updateBarber() {
    }

    @Test
    void deleteBarberById() {
    }
}