package com.barber.hopak.repository;

import com.barber.hopak.model.impl.Haircut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HaircutRepository extends JpaRepository<Haircut, Long> {
    Optional<Haircut> findByName(String name);
}
