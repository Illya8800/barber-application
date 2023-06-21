package com.barber.hopak.web.controller;

import com.barber.hopak.service.HaircutService;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.web.domain.impl.HaircutDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/haircuts")
@RequiredArgsConstructor
@Log4j2
public class HaircutController {
    private final HaircutService<HaircutDto, Long> haircutService;
    private final ImageService<ImageDto, Long> imageService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<HaircutDto> findHaircutById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findHaircutById\" mapping");
        return new ResponseEntity<>(haircutService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/name/{haircutName}")
    public ResponseEntity<HaircutDto> findHaircutByName(@PathVariable String haircutName) {
        log.info("Controller processing the GET \"findHaircutByName\" mapping");
        return new ResponseEntity<>(haircutService.findByName(haircutName), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<HaircutDto>> findAllHaircuts() {
        log.info("Controller processing the GET \"findAllHaircuts\" mapping");
        return new ResponseEntity<>(haircutService.findAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createHaircut(@ModelAttribute("haircutDto") @Valid HaircutDto haircutDto,
                                              @ModelAttribute("imageDto") @Valid ImageDto imageDto) {
        log.info("Controller processing the POST \"createHaircut\" mapping");
        imageService.setImageNameByOriginalFileName(imageDto);
        haircutDto.setAvatar(imageDto);
        haircutService.create(haircutDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateHaircut(@ModelAttribute("haircutDto") @Valid HaircutDto haircutDto,
                                              @ModelAttribute("imageDto") @Valid ImageDto imageDto) {
        log.info("Controller processing the PATCH \"updateHaircut\" mapping");
        imageService.setImageNameByOriginalFileName(imageDto);
        haircutDto.setAvatar(imageDto);
        haircutService.update(haircutDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteHaircutById(@PathVariable Long id) {
        log.info("Controller processing the DELETE \"deleteHaircutById\" mapping");
        haircutService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/unique")
    public ResponseEntity<Boolean> isUniqueHaircut(@RequestParam(name = "id") Long id, @RequestParam(name = "name") String name) {
        log.info("Controller processing the GET \"isUniqueHaircut\" mapping");
        return new ResponseEntity<>(haircutService.isUnique(id, name), HttpStatus.OK);
    }
}
