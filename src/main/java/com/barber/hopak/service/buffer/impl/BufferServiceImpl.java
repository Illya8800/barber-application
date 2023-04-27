package com.barber.hopak.service.buffer.impl;

import com.barber.hopak.org.springframework.web.multipart.custom.MultipartFileFromDateBase;
import com.barber.hopak.service.buffer.BufferManager;
import com.barber.hopak.service.buffer.BufferService;
import com.barber.hopak.service.buffer.BufferedFileName;
import com.barber.hopak.web.domain.impl.ImageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Log4j2
public class BufferServiceImpl implements BufferService<ImageDto> {
    private final BufferManager<File, Long> bufferManager;
    private final Set<String> imageSet = new HashSet<>();

    @Override
    public void saveImage(ImageDto imageDto) {
        log.info("Saving file as bytes in buffer");
        File file = bufferManager.save(imageDto);
        imageSet.add(file.getName());
    }
    @Override
    public Optional<ImageDto> findImageById(Long id) {
        log.info("Finding image in buffer with id {}", id);
        Optional<File> file = bufferManager.findFileById(id);
        return file.flatMap(this::buildImageDtoFromFile);
    }
    @Override
    public Optional<ImageDto> findImageByName(String name) {
        log.info("Finding image in buffer with name {}", name);
        Optional<File> file = bufferManager.findFileByName(name);
        return file.flatMap(this::buildImageDtoFromFile);
    }

    @Override
    public void deleteImageByName(String name) {
        log.info("Deleting file with name {}", name);
        bufferManager.findFileByName(name)
                .filter(File::exists)
                .filter(File::delete)
                .ifPresent(file -> log.info("File with name {} successfully deleted", name));
    }

    @Override
    public void deleteImageById(Long id) {
        log.info("Deleting file with id {}", id);
        bufferManager.findFileById(id)
                .filter(File::exists)
                .filter(File::delete)
                .ifPresent(file -> log.info("File with id {} successfully deleted", id));
    }

    private Optional<ImageDto> buildImageDtoFromFile(File file) {
        log.info("Building ImageDto by file {}", file.getName());
        BufferedFileName bufferedFileName = new BufferedFileName(file);
        return Optional.of(
                ImageDto.builder()
                        .id(bufferedFileName.getId())
                        .imageName(bufferedFileName.getImageName())
                        .image(new MultipartFileFromDateBase(bufferedFileName.getImageName(), bufferManager.getBytesByFile(file))).build());
    }
}