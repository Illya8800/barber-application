package com.barber.hopak.buffer.impl;

import com.barber.hopak.buffer.BufferState;
import com.barber.hopak.buffer.FileSearcher;
import com.barber.hopak.util.StringUtils3C;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import static com.barber.hopak.util.ImageUtil.DOT_TXT;
import static com.barber.hopak.util.ImageUtil.ID_SEPARATOR;

@Component
@RequiredArgsConstructor
@Log4j2
public class FileSearcherImpl implements FileSearcher {
    private final BufferState bufferState;

    @Override
    public Optional<File> getFileByName(String imageName) {
        final String FILE_NAME_REGEX = StringUtils3C.join("^\\d{1,19}", ID_SEPARATOR, imageName, DOT_TXT, "$");
        File[] files = new File(bufferState.getBufferPath()).listFiles();
        if (files != null)
            return Arrays.stream(files).filter(filterByFileNameRegex(FILE_NAME_REGEX)).findFirst();
        return Optional.empty();
    }

    @Override
    public Optional<File> getFileById(Long id) {
        final String FILE_NAME_REGEX = id + ID_SEPARATOR;
        File[] files = new File(bufferState.getBufferPath()).listFiles();
        if (files != null)
            return Arrays.stream(files).filter(filterById(FILE_NAME_REGEX)).findFirst();
        return Optional.empty();
    }

    private static Predicate<File> filterById(String FILE_NAME_REGEX) {
        return f -> f.getName().startsWith(FILE_NAME_REGEX);
    }

    private static Predicate<File> filterByFileNameRegex(String FILE_NAME_REGEX) {
        return f -> f.getName().matches(FILE_NAME_REGEX);
    }

}