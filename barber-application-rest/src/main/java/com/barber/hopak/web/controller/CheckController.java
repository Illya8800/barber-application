package com.barber.hopak.web.controller;

import com.barber.hopak.service.CheckService;
import com.barber.hopak.web.domain.impl.CheckDto;
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
//@RequestMapping("/checks")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Check Management", description = "APIs for managing checks")
public class CheckController {
    private final CheckService<CheckDto, Long> checkService;

    @Operation(summary = "Get a check by id", description = "Returns a check as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Not found - The check was not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<CheckDto> findCheckById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findCheckById\" mapping");
        return new ResponseEntity<>(checkService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Find all checks (paginated)", description = "Get a paginated list of all checks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the paginated list of checks"),
    })
    @GetMapping()
    public ResponseEntity<List<CheckDto>> findAllChecks() {
        log.info("Controller processing the GET \"findAllChecks\" mapping");
        return new ResponseEntity<>(checkService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Create an check", description = "Upload and create a new check.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Check created successfully"),
            @ApiResponse(responseCode = "422", description = "Probably illegal value(s) in request")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createCheck(@ModelAttribute("clientDto") @Valid CheckDto clientDto) {
        log.info("Controller processing the POST \"createCheck\" mapping");
        checkService.create(clientDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update an check", description = "Update an existing check's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Check updated successfully."),
            @ApiResponse(responseCode = "422", description = "Bad request. Probably illegal value(s) in request")
    })
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCheck(@ModelAttribute("clientDto") @Valid CheckDto clientDto) {
        log.info("Controller processing the PATCH \"updateCheck\" mapping");
        checkService.update(clientDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete an check", description = "Delete an existing check's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Check deleted successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCheckById(@PathVariable Long id) {
        log.info("Controller processing the DELETE \"deleteCheckById\" mapping");
        checkService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
