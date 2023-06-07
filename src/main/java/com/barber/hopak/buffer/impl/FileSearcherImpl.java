package com.barber.hopak.buffer.impl;

import com.barber.hopak.buffer.FileSearcher;
import com.barber.hopak.config.BufferPathConfig;
import com.barber.hopak.util.StringUtils3C;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import static com.barber.hopak.util.ImageUtil.BUFFER_FOLDER_NAME;
import static com.barber.hopak.util.ImageUtil.DOT_TXT;
import static com.barber.hopak.util.ImageUtil.FOLDER_SEPARATOR;
import static com.barber.hopak.util.ImageUtil.ID_SEPARATOR;

@Component
@RequiredArgsConstructor
@Log4j2
public class FileSearcherImpl implements FileSearcher {
    private final BufferPathConfig bufferPathConfig;
    private String BASIC_BUFFER_PATH;

    @PostConstruct
    void init() {
        BASIC_BUFFER_PATH = createBufferPackage();
    }

    @Override
    public Optional<File> getFileByName(String imageName) {
        final String FILE_NAME_REGEX = StringUtils3C.join("^\\d{1,19}", ID_SEPARATOR, imageName, DOT_TXT, "$");
        File[] files = new File(BASIC_BUFFER_PATH).listFiles();
        if (files != null)
            return Arrays.stream(files).filter(filterByFileNameRegex(FILE_NAME_REGEX)).findFirst();
        return Optional.empty();
    }

    @Override
    public Optional<File> getFileById(Long id) {
        final String FILE_NAME_REGEX = StringUtils3C.join(id, ID_SEPARATOR);
        File[] files = new File(BASIC_BUFFER_PATH).listFiles();
        if (files != null)
            return Arrays.stream(files).filter(filterById(FILE_NAME_REGEX)).findFirst();
        return Optional.empty();
    }

    @Override
    public String getBufferPath() {
        return BASIC_BUFFER_PATH;
    }

    private static Predicate<File> filterById(String FILE_NAME_REGEX) {
        return f -> f.getName().startsWith(FILE_NAME_REGEX);
    }

    private static Predicate<File> filterByFileNameRegex(String FILE_NAME_REGEX) {
        return f -> f.getName().matches(FILE_NAME_REGEX);
    }

    private String createBufferPackage() {
        String bufferFolderPath = StringUtils3C.join(bufferPathConfig.getPath(), FOLDER_SEPARATOR, BUFFER_FOLDER_NAME);
        if (new File(bufferFolderPath).mkdir()) {
            log.info("Folder \"{}\" has been created", BUFFER_FOLDER_NAME);
        }
        return bufferFolderPath;
    }

}