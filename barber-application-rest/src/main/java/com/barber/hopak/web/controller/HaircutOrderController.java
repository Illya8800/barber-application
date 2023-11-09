package com.barber.hopak.web.controller;

import com.barber.hopak.service.HaircutOrderService;
import com.barber.hopak.web.domain.impl.HaircutOrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

import java.util.List;

//@RestController
//@RequestMapping("/orders")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Haircut order Management", description = "APIs for managing haircut orders")
public class HaircutOrderController {
    private final HaircutOrderService<HaircutOrderDto, Long> haircutOrderService;

    @Operation(summary = "Get a haircut order by id", description = "Returns a haircut order as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Not found - The haircut order was not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<HaircutOrderDto> findHaircutOrderById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findHaircutOrderById\" mapping");
        return new ResponseEntity<>(haircutOrderService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Find all haircut orders (paginated)", description = "Get a paginated list of all haircut order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the paginated list of haircut orders"),
    })
    @GetMapping()
    public ResponseEntity<List<HaircutOrderDto>> findAllHaircutOrders() {
        log.info("Controller processing the GET \"findAllHaircutOrders\" mapping");
        return new ResponseEntity<>(haircutOrderService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Create a haircut order", description = "Upload and create a new a haircut order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Haircut order created successfully"),
            @ApiResponse(responseCode = "422", description = "Probably illegal value(s) in request")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createHaircutOrder(@ModelAttribute @Valid HaircutOrderDto haircutDto) {
        log.info("Controller processing the POST \"createHaircutOrder\" mapping");
        haircutOrderService.create(haircutDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update a haircut order", description = "Update an existing a haircut's order details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Haircut order updated successfully."),
            @ApiResponse(responseCode = "422", description = "Bad request. Probably illegal value(s) in request")
    })
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateHaircutOrder(@ModelAttribute @Valid HaircutOrderDto haircutDto) {
        log.info("Controller processing the PATCH \"updateHaircutOrder\" mapping");
        haircutOrderService.update(haircutDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete a haircut order", description = "Delete an existing haircut's order details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Haircut order deleted successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteHaircutOrderById(@PathVariable Long id) {
        log.info("Controller processing the DELETE \"deleteHaircutOrderById\" mapping");
        haircutOrderService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
