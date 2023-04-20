package com.barber.hopak.exception;

import com.barber.hopak.model.impl.Image;
import com.barber.hopak.repository.ImageRepository;
import com.barber.hopak.service.ImageService;
import com.barber.hopak.web.domain.impl.ImageDto;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@Log4j2
public class ImageServiceImpl implements ImageService<ImageDto, Long> {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ImageDto findById(Long id) {
        log.info("Finding an image with id = {} in DB", id);
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty()) {
            throw new RuntimeException(StringUtils.join(List.of("Image with id " + id + " doesn't exist"), null));
        }
        return image.get().toDto();
    }

    @Override
    @Transactional(readOnly = true)
    public ImageDto findByImageName(String imageName) {
        log.info("Finding an image with name = {} in DB", imageName);
        Image image = imageRepository.findByImageName(imageName)
                .orElseThrow(() -> new ImageNotFoundException("Image with name '" + imageName + "' not found"));

        return image.toDto();
    }

    @Override
    public List<ImageDto> findAllImages() {
        log.info("Finding all images in DB");
        return StreamSupport.stream(imageRepository.findAll().spliterator(), false)
                .map(Image::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ImageDto createImage(ImageDto imageDto) {
        log.info("Inserting new image with name = {} in DB ", imageDto.getImageName());
        if (!isUnique(imageDto)) {
            throw new ImageNotUniqueException(StringUtils.join(List.of("Image with name ", imageDto.getImageName(), " exists"), null));
        }
        return imageRepository.save(imageDto.toEntity()).toDto();
    }

    @Override
    public ImageDto updateImage(ImageDto imageDto) {
        log.info("Updating image with name = {} in DB ", imageDto.getImageName());
        if (!isUnique(imageDto)) {
            return imageRepository.save(imageDto.toEntity()).toDto();
        } else {
            throw new ImageNotFoundException(StringUtils.join(List.of("Image with name ", imageDto.getImageName(), " isn't found"), null));
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting a image with id = {} from DB", id);
        imageRepository.deleteById(id);
    }

    @Override
    public void delete(ImageDto imageDto) {
        log.info("Deleting a image with id = {} from DB", imageDto.getId());
        imageRepository.delete(imageDto.toEntity());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public boolean isUnique(ImageDto imageDto) {
        log.info("Checking imageName = {} on unique", imageDto.getImageName());
        return imageRepository.findByImageName(imageDto.getImageName()).map(value -> value.getId().equals(imageDto.getId())).orElse(true);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public void setImageNameByOriginalFileName(ImageDto imageDto) {
        imageDto.setImageName(imageDto.getImage().getOriginalFilename());
    }

}
