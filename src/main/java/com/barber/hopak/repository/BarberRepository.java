package com.barber.hopak.repository;

import com.barber.hopak.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarberRepository extends JpaRepository<Barber, Long> {
    Optional<Barber> findById(Long id);
}
