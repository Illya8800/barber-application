package com.barber.hopak.web.controller;

import com.barber.hopak.service.PaymentService;
import com.barber.hopak.web.domain.impl.PaymentDto;
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
@RequestMapping("/payments")
@RequiredArgsConstructor
@Log4j2
public class PaymentController {
    private final PaymentService<PaymentDto, Long> paymentService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PaymentDto> findPaymentById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findPaymentById\" mapping");
        return new ResponseEntity<>(paymentService.findById(id), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<PaymentDto>> findAllPayments() {
        log.info("Controller processing the GET \"findAllPayments\" mapping");
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createPayment(@ModelAttribute @Valid PaymentDto barberDto) {
        log.info("Controller processing the POST \"createPayment\" mapping");
        paymentService.create(barberDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Void> updatePayment(@ModelAttribute @Valid PaymentDto barberDto) {
        log.info("Controller processing the PATCH \"updatePayment\" mapping");
        paymentService.update(barberDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePaymentById(@PathVariable Long id) {
        log.info("Controller processing the DELETE \"deletePaymentById\" mapping");
        paymentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
