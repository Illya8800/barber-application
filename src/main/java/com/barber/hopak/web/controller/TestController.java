package com.barber.hopak.web.controller;

import com.barber.hopak.service.ImageService;
import com.barber.hopak.web.domain.impl.ImageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Log4j2
public class TestController {
    private final ImageService<ImageDto, Long> imageService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Long> findImageById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findImageById\" mapping");
        long l = System.currentTimeMillis();
        ImageDto byId = imageService.findById(id);
        long l1 = System.currentTimeMillis();

        return new ResponseEntity<>(l1 -l, HttpStatus.OK);
    }
}
