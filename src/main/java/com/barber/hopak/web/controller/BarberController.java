package com.barber.hopak.web.controller;

import com.barber.hopak.service.BarberService;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.web.domain.impl.BarberDto;
import com.barber.hopak.web.domain.impl.ImageDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/barbers")
@RequiredArgsConstructor
@Log4j2
public class BarberController {
    private final BarberService<BarberDto, Long> barberService;
    private final ImageService<ImageDto, Long> imageService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BarberDto> findBarberById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findBarberById\" mapping");
        return new ResponseEntity<>(barberService.findById(id), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<BarberDto>> findAllBarbers() {
        log.info("Controller processing the GET \"findAllBarbers\" mapping");
        return new ResponseEntity<>(barberService.findAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createBarber(@ModelAttribute("barberDto") @Valid BarberDto barberDto,
                                             @ModelAttribute("imageDto") @Valid ImageDto imageDto) {
        log.info("Controller processing the POST \"createBarber\" mapping");
        imageService.setImageNameByOriginalFileName(imageDto);
        barberDto.setAvatar(imageDto);
        barberService.create(barberDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateBarber(@ModelAttribute("barberDto") @Valid BarberDto barberDto,
                                             @ModelAttribute("imageDto") @Valid ImageDto imageDto) {
        log.info("Controller processing the PATCH \"updateBarber\" mapping");
        imageService.setImageNameByOriginalFileName(imageDto);
        barberDto.setAvatar(imageDto);
        barberService.update(barberDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBarberById(@ModelAttribute("barberDto") @Valid BarberDto barberDto) {
        log.info("Controller processing the DELETE \"deleteBarberById\" mapping");
        barberService.delete(barberDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
