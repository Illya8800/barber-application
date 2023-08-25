package com.barber.hopak.service.impl;

import com.barber.hopak.buffer.impl.BufferServiceImpl;
import com.barber.hopak.exception.entity.barber.BarberNotFoundException;
import com.barber.hopak.model.impl.Barber;
import com.barber.hopak.repository.BarberRepository;
import com.barber.hopak.service.BarberService;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.web.domain.impl.BarberDto;
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
public class BarberServiceImpl implements BarberService<BarberDto, Long> {
    private final BarberRepository barberRepository;
    private final ImageService<ImageDto, Long> imageService;
    private final BufferServiceImpl bufferService;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public BarberDto findById(Long id) {
        log.info("Finding an barber with id = {} in DB", id);
        return barberRepository.findById(id)
                .map(barber -> {
                    ImageDto avatarDto = imageService.findById(barber.getAvatarId());
                    BarberDto dtoBarber = barber.toDto();
                    dtoBarber.setAvatar(avatarDto);
                    return dtoBarber;
                })
                .orElseThrow(() -> new BarberNotFoundException(StringUtils3C.join("Barber with id ", id, " not found")));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<BarberDto> findAll() {
        log.info("Finding all barbers in DB");
        return barberRepository.findAll().stream()
                .map(Barber::toDto)
                .toList();
    }

    @Override
    public BarberDto create(BarberDto barberDto) {
        log.info("Inserting new barber with name = {} in DB ", barberDto.getBarberName());
        Barber createdBarber = barberRepository.save(barberDto.toEntity());
        createdBarber.setAvatarId(createdBarber.getAvatar().getId());
        BarberDto createdBarberDto = createdBarber.toDto();
        bufferService.save(createdBarberDto.getAvatar());
        return createdBarberDto;
    }

    @Override
    public BarberDto update(BarberDto barberDto) {
        log.info("Updating barber with name = {} in DB ", barberDto.getBarberName());
        BarberDto updatedBarberDto = barberRepository.save(barberDto.toEntity()).toDto();
        bufferService.save(updatedBarberDto.getAvatar());
        return updatedBarberDto;
    }

    @Override
    public void delete(BarberDto barberDto) {
        log.info("Deleting a barber with id = {} from DB", barberDto.getId());
        barberRepository.deleteById(barberDto.getId());
        bufferService.deleteImageById(barberDto.getAvatarId());
    }
}