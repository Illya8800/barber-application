package com.barber.hopak.repository;

import com.barber.hopak.model.impl.HaircutOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HaircutOrderRepository extends JpaRepository<HaircutOrder, Long> {
}
