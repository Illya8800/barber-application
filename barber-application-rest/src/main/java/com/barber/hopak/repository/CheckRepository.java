package com.barber.hopak.repository;

import com.barber.hopak.model.impl.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckRepository extends JpaRepository<Check, Long> {
}
