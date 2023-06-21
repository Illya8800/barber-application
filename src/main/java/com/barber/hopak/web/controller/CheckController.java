package com.barber.hopak.web.controller;

import com.barber.hopak.service.CheckService;
import com.barber.hopak.web.domain.impl.CheckDto;
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
@RequestMapping("/checks")
@RequiredArgsConstructor
@Log4j2
public class CheckController {
    private final CheckService<CheckDto, Long> checkService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CheckDto> findCheckById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findCheckById\" mapping");
        return new ResponseEntity<>(checkService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CheckDto>> findAllChecks() {
        log.info("Controller processing the GET \"findAllChecks\" mapping");
        return new ResponseEntity<>(checkService.findAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createCheck(@ModelAttribute("clientDto") @Valid CheckDto clientDto) {
        log.info("Controller processing the POST \"createCheck\" mapping");
        checkService.create(clientDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCheck(@ModelAttribute("clientDto") @Valid CheckDto clientDto) {
        log.info("Controller processing the PATCH \"updateCheck\" mapping");
        checkService.update(clientDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCheckById(@PathVariable Long id) {
        log.info("Controller processing the DELETE \"deleteCheckById\" mapping");
        checkService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
