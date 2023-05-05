package com.barber.hopak.buffer;

import com.barber.hopak.web.domain.impl.ImageDto;

import java.io.File;
import java.util.Optional;

public interface BufferManager<T, ID> {
    File save(ImageDto imageDto);

    Optional<T> findFileById(ID id);

    Optional<T> findFileByName(String imageName);

    byte[] getBytesByFile(File file);
}
