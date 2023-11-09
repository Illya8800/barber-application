package com.barber.hopak.web.controller;

import com.barber.hopak.service.PaymentService;
import com.barber.hopak.web.domain.impl.PaymentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import java.util.List;

//@RestController
//@RequestMapping("/payments")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Payment Management", description = "APIs for managing payments")
public class PaymentController {
    private final PaymentService<PaymentDto, Long> paymentService;

    @Operation(summary = "Get a payment by id", description = "Returns a payment as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Not found - The payment was not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<PaymentDto> findPaymentById(@PathVariable @Parameter(name = "id", description = "A payment id", example = "1") Long id) {
        log.info("Controller processing the GET \"findPaymentById\" mapping");
        return new ResponseEntity<>(paymentService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Find all payments (paginated)", description = "Get a paginated list of all payments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the paginated list of payments"),
    })
    @GetMapping()
    public ResponseEntity<List<PaymentDto>> findAllPayments() {
        log.info("Controller processing the GET \"findAllPayments\" mapping");
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Create an payment", description = "Upload and create a new payment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "422", description = "Bad request. Probably illegal value(s) in request")
    })
    @PostMapping
    public ResponseEntity<Void> createPayment(@Valid @ModelAttribute PaymentDto paymentDto) {
        log.info("Controller processing the POST \"createPayment\" mapping");
        paymentService.create(paymentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update an payment", description = "Update an existing payment's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Payment updated successfully."),
            @ApiResponse(responseCode = "422", description = "Bad request. Probably illegal value(s) in request")
    })
    @PatchMapping
    public ResponseEntity<Void> updatePayment(@ModelAttribute @Valid PaymentDto barberDto) {
        log.info("Controller processing the PATCH \"updatePayment\" mapping");
        paymentService.update(barberDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete a payment", description = "Delete an existing payment's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Payment deleted successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePaymentById(@PathVariable Long id) {
        log.info("Controller processing the DELETE \"deletePaymentById\" mapping");
        paymentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}