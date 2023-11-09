package com.barber.hopak.web.controller;

import com.barber.hopak.service.ClientService;
import com.barber.hopak.web.domain.impl.ClientDto;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@RestController
//@RequestMapping("/clients")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Client Management", description = "APIs for managing clients")
public class ClientController {
    private final ClientService<ClientDto, Long> clientService;

    @Operation(summary = "Get a client by id", description = "Returns a client as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Not found - The client was not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findClientById\" mapping");
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get a client by phoneNumber", description = "Returns a client as per the phoneNumber")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Not found - The haircut was not found")
    })
    @GetMapping("/name/{phoneNumber}")
    public ResponseEntity<ClientDto> findClientByPhoneNumber(@PathVariable String phoneNumber) {
        log.info("Controller processing the GET \"findClientByPhoneNumber\" mapping");
        return new ResponseEntity<>(clientService.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

    @Operation(summary = "Find all clients (paginated)", description = "Get a paginated list of all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the paginated list of clients"),
    })
    @GetMapping
    public ResponseEntity<List<ClientDto>> findAllClients() {
        log.info("Controller processing the GET \"findAllClients\" mapping");
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Create an client", description = "Upload and create a new client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created successfully"),
            @ApiResponse(responseCode = "422", description = "Probably illegal value(s) in request")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createClient(@ModelAttribute("clientDto") @Valid ClientDto clientDto) {
        log.info("Controller processing the POST \"createClient\" mapping");
        clientService.create(clientDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update an client", description = "Update an existing client's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client updated successfully."),
            @ApiResponse(responseCode = "422", description = "Bad request. Probably illegal value(s) in request")
    })
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateClient(@ModelAttribute("clientDto") @Valid ClientDto clientDto) {
        log.info("Controller processing the PATCH \"updateClient\" mapping");
        clientService.update(clientDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete an client", description = "Delete an existing client's details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client deleted successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id) {
        log.info("Controller processing the DELETE \"deleteClientById\" mapping");
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Checks a client on unique", description = "Checks a client by name and id on unique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The checking result"),
    })
    @GetMapping("/unique")
    public ResponseEntity<Boolean> isUniqueClient(@RequestParam(name = "id") Long id, @RequestParam(name = "name") String name) {
        log.info("Controller processing the GET \"isUniqueClient\" mapping");
        return new ResponseEntity<>(clientService.isUnique(id, name), HttpStatus.OK);
    }
}
