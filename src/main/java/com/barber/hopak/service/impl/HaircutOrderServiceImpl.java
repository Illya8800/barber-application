package com.barber.hopak.service.impl;

import com.barber.hopak.exception.haircut.order.HaircutOrderNotFoundException;
import com.barber.hopak.model.impl.HaircutOrder;
import com.barber.hopak.repository.HaircutOrderRepository;
import com.barber.hopak.service.HaircutOrderService;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.web.domain.impl.HaircutOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class HaircutOrderServiceImpl implements HaircutOrderService<HaircutOrderDto, Long> {
    private final HaircutOrderRepository haircutOrderRepository;

    @Override
    @Transactional(readOnly = true)
    public HaircutOrderDto findById(Long id) {
        log.info("Finding an haircut order with id = {} in DB", id);
        return haircutOrderRepository.findById(id)
                .orElseThrow(() -> new HaircutOrderNotFoundException(StringUtils3C.join("Haircut order with id ", id, " not found"))).toDto();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HaircutOrderDto> findAll() {
        log.info("Finding all haircut orders in DB");
        return haircutOrderRepository.findAll().stream()
                .map(HaircutOrder::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HaircutOrderDto create(HaircutOrderDto haircutOrderDto) {
        log.info("Inserting new haircut order with id = {} in DB ", haircutOrderDto.getId());
        return haircutOrderRepository.save(haircutOrderDto.toEntity()).toDto();
    }

    @Override
    public HaircutOrderDto update(HaircutOrderDto haircutOrderDto) {
        log.info("Updating haircut order with id = {} in DB ", haircutOrderDto.getId());
        return haircutOrderRepository.save(haircutOrderDto.toEntity()).toDto();
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting a haircut order with id = {} from DB", id);
        haircutOrderRepository.deleteById(id);
    }
}