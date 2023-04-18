package com.barber.hopak.web.controller;

import com.barber.hopak.service.ImageService;
import com.barber.hopak.web.domain.impl.ImageDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@Log4j2
public class ImageController {
    private final ImageService<ImageDto, Long> imageService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ImageDto> findImageById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findImageById\" mapping");
        return new ResponseEntity<>(imageService.findById(id), HttpStatus.OK);
    }

    @GetMapping("name/{imageDto}")
    public ResponseEntity<ImageDto> findImageByImageName(@PathVariable ImageDto imageDto) {
        log.info("Controller processing the GET \"findImageByImageName\" mapping");
        return new ResponseEntity<>(imageDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> findAllImages() {
        log.info("Controller processing the GET \"findAllImages\" mapping");
        return new ResponseEntity<>(imageService.findAllImages(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createImage(@Valid @ModelAttribute ImageDto imageDto) {
        log.info("Controller processing the POST \"createImage\" mapping");
        imageService.setImageNameByOriginalFileName(imageDto);
        imageService.createImage(imageDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Void> updateImage(@Valid @ModelAttribute ImageDto imageDto) {
        log.info("Controller processing the PATCH \"updateImage\" mapping");
        imageService.setImageNameByOriginalFileName(imageDto);
        imageService.createImage(imageDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        log.info("Controller processing the DELETE \"deleteImage\" mapping");
        imageService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("unique")
    public ResponseEntity<Boolean> isUniqueImage(@RequestParam ImageDto imageDto) {
        log.info("Controller processing the GET \"isUniqueImage\" mapping");
        return new ResponseEntity<>(imageService.isUnique(imageDto), HttpStatus.OK);
    }
}