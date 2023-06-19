package com.barber.hopak.repository;

import com.barber.hopak.model.impl.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {
    @Query("SELECT b FROM Barber b JOIN FETCH b.avatar WHERE b.id = ?1")
    Optional<Barber> findById(Long id);
}
