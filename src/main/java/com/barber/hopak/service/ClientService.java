package com.barber.hopak.service;

import com.barber.hopak.model.impl.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ClientService extends JpaRepository<Client, Long> {
    Optional<Client> findById(Long id);
}
