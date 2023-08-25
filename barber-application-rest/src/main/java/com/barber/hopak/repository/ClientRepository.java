package com.barber.hopak.repository;

import com.barber.hopak.model.impl.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    Optional<Client> findByPhoneNumber(String phoneNumber);
}
