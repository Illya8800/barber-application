package com.barber.hopak.repository;

import com.barber.hopak.model.impl.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {
}
