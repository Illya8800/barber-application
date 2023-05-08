package com.barber.hopak.service.impl;

import com.barber.hopak.buffer.BufferService;
import com.barber.hopak.exception.image.ImageNotFoundException;
import com.barber.hopak.exception.image.ImageNotUniqueException;
import com.barber.hopak.model.enumeration.ImageExtensions;
import com.barber.hopak.model.impl.Image;
import com.barber.hopak.repository.ImageRepository;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.util.StringUtils3C;
import com.barber.hopak.web.domain.impl.ImageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService<ImageDto, Long> {
    private final BufferService<ImageDto> bufferService;
    private final ImageRepository imageRepository;

    @Override
    @Transactional(readOnly = true)
    public ImageDto findById(Long id) {
        log.info("Finding an image with id = {} in DB", id);
        return bufferService.findImageById(id)
                .orElseGet(() -> {
                    Optional<Image> image = imageRepository.findById(id);
                    image.ifPresent(i -> bufferService.save(i.toDto()));
                    return image.orElseThrow(() -> new ImageNotFoundException(StringUtils3C.join("Image with id ", id, " doesn't exist")))
                            .toDto();
                });
    }

    @Override
    @Transactional(readOnly = true)
    public ImageDto findByImage(String imageName) {
        log.info("Finding an image with name = {} in DB", imageName);
        return bufferService.findImageByName(imageName)
                .orElseGet(() -> {
                    Optional<Image> image = imageRepository.findByName(imageName);
                    image.ifPresent(i -> bufferService.save(i.toDto()));
                    return image.orElseThrow(() -> new ImageNotFoundException("Image with name '" + imageName + "' not found"))
                            .toDto();
                });
    }

    @Override
    public List<ImageDto> findAllImages() {
        log.info("Finding all images in DB");
        return StreamSupport.stream(imageRepository.findAll().spliterator(), false)
                .map(Image::toDto)
                .peek(bufferService::save)
                .collect(Collectors.toList());
    }

    @Override
    public ImageDto create(ImageDto imageDto) {
        log.info("Inserting new image with name = {} in DB ", imageDto.getName());
        if (!isUnique(imageDto)) {
            throw new ImageNotUniqueException(StringUtils3C.join("Image with name ", imageDto.getName(), " exists"));
        }
        ImageDto savedImage = imageRepository.save(imageDto.toEntity()).toDto();
        bufferService.save(savedImage);
        return savedImage;
    }

    @Override
    public ImageDto update(ImageDto imageDto) {
        log.info("Updating image with name = {} in DB ", imageDto.getName());
        if (!isUnique(imageDto)) {
            ImageDto updatedImage = imageRepository.save(imageDto.toEntity()).toDto();
            bufferService.save(updatedImage);
            return updatedImage;
        } else {
            throw new ImageNotFoundException(StringUtils3C.join("Image with name ", imageDto.getName(), " isn't found"));
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting a image with id = {} from DB", id);
        imageRepository.deleteById(id);
        bufferService.deleteImageById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public boolean isUnique(ImageDto imageDto) {
        log.info("Checking imageName = {} on unique", imageDto.getName());
        return imageRepository.findByName(imageDto.getName()).map(value -> value.getId().equals(imageDto.getId())).orElse(true);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public void setImageNameByOriginalFileName(ImageDto imageDto) {
        imageDto.setName(imageDto.getImage().getOriginalFilename());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean isExtensionValid(String fileName) {
        Optional<Map.Entry<String, String>> first = ImageExtensions.getExtensions().entrySet()
                .stream()
                .filter(entry -> Objects.requireNonNull(fileName).endsWith(entry.getValue()))
                .findFirst();
        return first.isPresent();
    }
}
