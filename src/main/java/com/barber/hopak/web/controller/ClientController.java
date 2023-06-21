package com.barber.hopak.web.controller;

import com.barber.hopak.service.ClientService;
import com.barber.hopak.web.domain.impl.ClientDto;
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
@RequestMapping("/clients")
@RequiredArgsConstructor
@Log4j2
public class ClientController {
    private final ClientService<ClientDto, Long> clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable Long id) {
        log.info("Controller processing the GET \"findClientById\" mapping");
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/name/{phoneNumber}")
    public ResponseEntity<ClientDto> findClientByPhoneNumber(@PathVariable String phoneNumber) {
        log.info("Controller processing the GET \"findClientByPhoneNumber\" mapping");
        return new ResponseEntity<>(clientService.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<ClientDto>> findAllClients() {
        log.info("Controller processing the GET \"findAllClients\" mapping");
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createClient(@ModelAttribute("clientDto") @Valid ClientDto clientDto) {
        log.info("Controller processing the POST \"createClient\" mapping");
        clientService.create(clientDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateClient(@ModelAttribute("clientDto") @Valid ClientDto clientDto) {
        log.info("Controller processing the PATCH \"updateClient\" mapping");
        clientService.update(clientDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id) {
        log.info("Controller processing the DELETE \"deleteClientById\" mapping");
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/unique")
    public ResponseEntity<Boolean> isUniqueImage(@RequestParam(name = "id") Long id, @RequestParam(name = "name") String name) {
        log.info("Controller processing the GET \"isUniqueImage\" mapping");
        return new ResponseEntity<>(clientService.isUnique(id, name), HttpStatus.OK);
    }
}
