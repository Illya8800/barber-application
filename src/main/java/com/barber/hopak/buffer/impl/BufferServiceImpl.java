package com.barber.hopak.buffer.impl;

import com.barber.hopak.buffer.BufferContainerState;
import com.barber.hopak.buffer.BufferManager;
import com.barber.hopak.buffer.BufferService;
import com.barber.hopak.buffer.BufferedFileName;
import com.barber.hopak.org.springframework.web.multipart.custom.MultipartFileFromDateBase;
import com.barber.hopak.web.domain.impl.ImageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class BufferServiceImpl implements BufferService<ImageDto> {
    private final BufferManager<File, Long> bufferManager;
    private final BufferContainerState containerState;

    @Override
    public void save(ImageDto imageDto) {
        log.info("Saving file as bytes in buffer");
        if (!containerState.isBufferContain(imageDto.getName())) {
            File file = bufferManager.save(imageDto);
            containerState.add(file.getName());
        }
    }

    @Override
    public Optional<ImageDto> findImageById(Long id) {
        log.info("Finding image in buffer with id {}", id);
        if (containerState.isBufferContain(id)) {
            Optional<File> file = bufferManager.findFileById(id);
            return file.flatMap(this::buildImageDtoFromFile);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ImageDto> findImageByName(String name) {
        log.info("Finding image in buffer with name {}", name);
        if (containerState.isBufferContain(name)) {
            Optional<File> file = bufferManager.findFileByName(name);
            return file.flatMap(this::buildImageDtoFromFile);
        }
        return Optional.empty();
    }

    @Override
    public void deleteImageByName(String name) {
        log.info("Deleting file with name {}", name);
        bufferManager.findFileByName(name)
                .filter(File::exists)
                .filter(File::delete)
                .ifPresent(file -> containerState.removeByName(name));
    }

    @Override
    public void deleteImageById(Long id) {
        log.info("Deleting file with id {}", id);
        bufferManager.findFileById(id)
                .filter(File::exists)
                .filter(File::delete)
                .ifPresent(file -> containerState.removeById(id));
    }

    private Optional<ImageDto> buildImageDtoFromFile(File file) {
        log.info("Building ImageDto by file {}", file.getName());
        BufferedFileName bufferedFileName = new BufferedFileName(file);
        return Optional.of(
                ImageDto.builder()
                        .id(bufferedFileName.getId())
                        .name(bufferedFileName.getImageName())
                        .image(new MultipartFileFromDateBase(bufferedFileName.getImageName(), bufferManager.getBytesByFile(file))).build());
    }
}