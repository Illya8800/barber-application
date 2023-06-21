package com.barber.hopak.service.impl;

import com.barber.hopak.exception.check.CheckNotFoundException;
import com.barber.hopak.model.impl.Check;
import com.barber.hopak.repository.CheckRepository;
import com.barber.hopak.service.CheckService;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.web.domain.impl.CheckDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService<CheckDto, Long> {
    private final CheckRepository checkRepository;
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CheckDto findById(Long id) {
        log.info("Finding an check with id = {} in DB", id);
        return checkRepository.findById(id)
                .orElseThrow(() -> new CheckNotFoundException(StringUtils3C.join("Check with id ", id, " not found"))).toDto();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CheckDto> findAll() {
        log.info("Finding all checks in DB");
        return checkRepository.findAll().stream()
                .map(Check::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CheckDto create(CheckDto checkDto) {
        log.info("Inserting new check with id = {} in DB ", checkDto.getId());
        return checkRepository.save(checkDto.toEntity()).toDto();
    }

    @Override
    public CheckDto update(CheckDto checkDto) {
        log.info("Updating check with id = {} in DB ", checkDto.getId());
        return checkRepository.save(checkDto.toEntity()).toDto();
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting a check with id = {} from DB", id);
        checkRepository.deleteById(id);
    }
}