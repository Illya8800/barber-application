package com.barber.hopak.service.impl;

import com.barber.hopak.exception.haircut.HaircutNotFoundException;
import com.barber.hopak.model.impl.Haircut;
import com.barber.hopak.repository.HaircutRepository;
import com.barber.hopak.service.HaircutService;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.web.domain.impl.HaircutDto;
import com.barber.hopak.web.domain.impl.ImageDto;
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
public class HaircutServiceImpl implements HaircutService<HaircutDto, Long> {
    private final HaircutRepository haircutRepository;
    private final ImageService<ImageDto, Long> imageService;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public HaircutDto findById(Long id) {
        log.info("Finding an haircut with id = {} in DB", id);
        return haircutRepository.findById(id)
                .map(Haircut::toDto)
                .map(haircutDto -> {
                    haircutDto.setAvatar(imageService.findById(haircutDto.getAvatarId()));
                    return haircutDto;
                })
                .orElseThrow(() -> new HaircutNotFoundException(StringUtils3C.join("Haircut with id ", id, " not found")));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public HaircutDto findByName(String name) {
        log.info("Finding an haircut with name = {} in DB", name);
        return haircutRepository.findByName(name)
                .map(Haircut::toDto)
                .map(haircutDto -> {
                    haircutDto.setAvatar(imageService.findById(haircutDto.getAvatarId()));
                    return haircutDto;
                })
                .orElseThrow(() -> new HaircutNotFoundException(StringUtils3C.join("Haircut with name ", name, " not found")));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<HaircutDto> findAll() {
        log.info("Finding all haircuts in DB");
        return haircutRepository.findAll().stream()
                .map(Haircut::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HaircutDto create(HaircutDto haircutDto) {
        log.info("Inserting new haircut with name = {} in DB ", haircutDto.getName());
        HaircutDto savedHaircutDto = haircutRepository.save(haircutDto.toEntity()).toDto();
        imageService.create(savedHaircutDto.getAvatar());
        return savedHaircutDto;
    }

    @Override
    public HaircutDto update(HaircutDto haircutDto) {
        log.info("Updating haircut with name = {} in DB ", haircutDto.getName());
        HaircutDto updatedHaircutDto = haircutRepository.save(haircutDto.toEntity()).toDto();
        imageService.update(updatedHaircutDto.getAvatar());
        return updatedHaircutDto;
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting a haircut with id = {} from DB", id);
        haircutRepository.deleteById(id);
        imageService.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public boolean isUnique(Long id, String name) {
        log.info("Checking haircut name = {} on unique", name);
        return haircutRepository.findByName(name).map(value -> value.getId().equals(id)).orElse(true);
    }
}