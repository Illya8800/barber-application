package com.barber.hopak.buffer.impl;

import com.barber.hopak.buffer.BufferState;
import com.barber.hopak.config.BufferPathConfig;
import com.barber.hopak.exception.buffer.BufferCantBeDeletedException;
import com.barber.hopak.exception.buffer.BufferedFileCantBeDeletedException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Log4j2
public class BufferStateImpl implements BufferState {
    private final BufferPathConfig bufferPathConfig;
    private static String BUFFER_PATH;

    @PostConstruct
    private void createBuffer() {
        init();
    }

    @PreDestroy
    private void destroyBuffer() {
        destroy();
    }

    @Override
    public void init() {
        log.info("Creating buffer");
        createBufferPackage();
    }

    @Override
    public void destroy() {
        log.info("Destroying buffer");
        clearBuffer();
    }

    @Override
    public String getBufferPath() {
        return BUFFER_PATH;
    }

    private void createBufferPackage() {
        log.info("Creating the buffer folder");
        String bufferFolderPath = bufferPathConfig.getPath();
        if (new File(bufferFolderPath).mkdir()) {
            log.info("Buffer folder has been created \"{}\"", bufferFolderPath);
        }
        BUFFER_PATH = bufferFolderPath;
    }

    private void clearBuffer() {
        log.info("Deleting the bufferFolder");
        File bufferFolder = new File(bufferPathConfig.getPath());
        if (bufferFolder.exists()) deleteFiles(bufferFolder);
    }

    private void deleteFiles(File bufferFolder) {
        log.info("Deleting a files in bufferFolder");
        File[] files = bufferFolder.listFiles();
        if (files != null) {
            log.info("Found {} file(s) in buffer folder", files.length);
            Arrays.stream(files)
                    .forEach(file -> dropFile(bufferFolder, file.getName()));
        }
        boolean deleted = bufferFolder.delete();
        if (!deleted) throw new BufferCantBeDeletedException("Buffer doesn't deleted");
    }

    private void dropFile(File bufferFolder, String fileName) {
        boolean isFileDeleted = new File(bufferFolder, fileName).delete();
        if (!isFileDeleted)
            throw new BufferedFileCantBeDeletedException("Buffered file can't be deleted");
    }
}

