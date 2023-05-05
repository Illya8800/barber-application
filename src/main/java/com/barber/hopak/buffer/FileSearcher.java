package com.barber.hopak.buffer;

import java.io.File;
import java.util.Optional;

public interface FileSearcher {
    Optional<File> getFileByName(String imageName);

    Optional<File> getFileById(Long id);

    String getBufferPath();
}
