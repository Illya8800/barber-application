package com.barber.hopak.service.impl;

import com.barber.hopak.exception.entity.client.ClientNotFoundException;
import com.barber.hopak.model.impl.Client;
import com.barber.hopak.repository.ClientRepository;
import com.barber.hopak.service.ClientService;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.web.domain.impl.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService<ClientDto, Long> {
    private final ClientRepository clientRepository;
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ClientDto findById(Long id) {
        log.info("Finding an client with id = {} in DB", id);
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(StringUtils3C.join("Client with id ", id, " not found"))).toDto();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ClientDto findByPhoneNumber(String phoneNumber) {
        log.info("Finding an client with phone number = {} in DB", phoneNumber);
        return clientRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ClientNotFoundException(StringUtils3C.join("Client with phone number ", phoneNumber, " not found"))).toDto();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ClientDto> findAll() {
        log.info("Finding all clients in DB");
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .map(Client::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto create(ClientDto clientDto) {
        log.info("Inserting new client with lastname = {} in DB ", clientDto.getLastname());
        return clientRepository.save(clientDto.toEntity()).toDto();
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        log.info("Updating client with lastname = {} in DB ", clientDto.getLastname());
        return clientRepository.save(clientDto.toEntity()).toDto();
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting a client with id = {} from DB", id);
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public boolean isUnique(Long id, String phoneNumber) {
        log.info("Checking phone number = {} on unique", phoneNumber);
        return clientRepository.findByPhoneNumber(phoneNumber).map(value -> value.getId().equals(id)).orElse(false);
    }
}