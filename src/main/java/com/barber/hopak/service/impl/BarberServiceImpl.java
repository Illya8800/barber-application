package com.barber.hopak.service.impl;

import com.barber.hopak.exception.BarberNotFoundException;
import com.barber.hopak.model.impl.Barber;
import com.barber.hopak.repository.BarberRepository;
import com.barber.hopak.service.BarberService;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.web.domain.impl.BarberDto;
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
public class BarberServiceImpl implements BarberService<BarberDto, Long> {
    private final BarberRepository barberRepository;

    @Override
    @Transactional(readOnly = true)
    public BarberDto findById(Long id) {
        log.info("Finding an barber with id = {} in DB", id);
        return barberRepository.findById(id)
                .orElseThrow(() -> new BarberNotFoundException(StringUtils3C.join("Barber with id ", id, " not found"))).toDto();
    }

    @Override
    public List<BarberDto> findAll() {
        log.info("Finding all barbers in DB");
        return barberRepository.findAll().stream()
                .map(Barber::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BarberDto create(BarberDto barberDto) {
        log.info("Inserting new barber with name = {} in DB ", barberDto.getBarberName());
        return barberRepository.save(barberDto.toEntity()).toDto();
    }

    @Override
    public BarberDto update(BarberDto barberDto) {
        log.info("Updating barber with name = {} in DB ", barberDto.getBarberName());
        return barberRepository.save(barberDto.toEntity()).toDto();
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting a barber with id = {} from DB", id);
        barberRepository.deleteById(id);
    }
}
