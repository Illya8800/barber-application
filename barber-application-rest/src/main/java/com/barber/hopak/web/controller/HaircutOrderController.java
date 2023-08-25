package com.barber.hopak.web.controller;

import com.barber.hopak.service.HaircutOrderService;
import com.barber.hopak.web.domain.impl.HaircutOrderDto;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "HaircutOrder Management", description = "APIs for managing haircut orders")
public class HaircutOrderController {
    private final HaircutOrderService<HaircutOrderDto, Long> haircutOrderService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<HaircutOrderDto> findHaircutOrderById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findHaircutOrderById\" mapping");
        return new ResponseEntity<>(haircutOrderService.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<HaircutOrderDto>> findAllHaircutOrders() {
        log.info("Controller processing the GET \"findAllHaircutOrders\" mapping");
        return new ResponseEntity<>(haircutOrderService.findAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createHaircutOrder(@ModelAttribute @Valid HaircutOrderDto haircutDto) {
        log.info("Controller processing the POST \"createHaircutOrder\" mapping");
        haircutOrderService.create(haircutDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateHaircutOrder(@ModelAttribute @Valid HaircutOrderDto haircutDto) {
        log.info("Controller processing the PATCH \"updateHaircutOrder\" mapping");
        haircutOrderService.update(haircutDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteHaircutOrderById(@PathVariable Long id) {
        log.info("Controller processing the DELETE \"deleteHaircutOrderById\" mapping");
        haircutOrderService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
