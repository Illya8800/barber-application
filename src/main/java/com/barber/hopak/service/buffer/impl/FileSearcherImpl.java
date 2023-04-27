package com.barber.hopak.service.buffer.impl;

import com.barber.hopak.exception.ImagesBufferNotFount;
import com.barber.hopak.service.buffer.FileSearcher;
import com.barber.hopak.util.StringUtils3C;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Component
@Log4j2
public class FileSearcherImpl implements FileSearcher {
    private static final String DOT_TXT = ".txt";
    private static final String ID_SEPARATOR = "-";
    private final String BASIC_BUFFER_PATH = createBufferPackage();
    private static final String BUFFER_FOLDER_NAME = "images";
    private static final String FOLDER_SEPARATOR = "/";

    @Override
    public Optional<File> getFileByName(String imageName) {
        final String FILE_NAME_REGEX = StringUtils3C.join("^\\d{1,19}", ID_SEPARATOR, imageName, DOT_TXT, "$");
        return Arrays.stream(Objects.requireNonNull(new File(BASIC_BUFFER_PATH).listFiles())).filter(filterByFileNameRegex(FILE_NAME_REGEX)).findFirst();
    }
    @Override
    public Optional<File> getFileById(Long id) {
        final String FILE_NAME_REGEX = StringUtils3C.join(id, ID_SEPARATOR);
        return Arrays.stream(Objects.requireNonNull(new File(BASIC_BUFFER_PATH).listFiles())).filter(filterById(FILE_NAME_REGEX)).findFirst();
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
        try {
            String bufferFolderPath = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent() + FOLDER_SEPARATOR + BUFFER_FOLDER_NAME;
            if (new File(bufferFolderPath).mkdir()) {
                log.info("Folder \"{}\" has been created", BUFFER_FOLDER_NAME);
            }
            return bufferFolderPath;
        } catch (URISyntaxException e) {
            throw new ImagesBufferNotFount("Images buffer doesn't exist");
        }
    }

}
