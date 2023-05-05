package com.barber.hopak.buffer;

import com.barber.hopak.util.StringUtils3C;
import lombok.Getter;

import java.io.File;
import java.util.Objects;

import static com.barber.hopak.util.ImageUtil.DOT_TXT;
import static com.barber.hopak.util.ImageUtil.ID_SEPARATOR;

@Getter
public class BufferedFileName {
    private final Long id;
    private final String imageName;

    public BufferedFileName(Long id, String imageName) {
        this.id = Objects.requireNonNull(id);
        this.imageName = Objects.requireNonNull(imageName);
    }

    public BufferedFileName(File file) {
        Objects.requireNonNull(file);
        this.id = getIdFromBufferedFileName(file.getName());
        this.imageName = getNameFromBufferedFileName(file.getName());
    }

    public String getFileName() {
        return StringUtils3C.join(id, ID_SEPARATOR, imageName, DOT_TXT);
    }

    public Long getIdFromBufferedFileName(String fileName) {
        return Long.valueOf(fileName.substring(0, fileName.indexOf(ID_SEPARATOR)));
    }

    public String getNameFromBufferedFileName(String fileName) {
        return fileName.substring(fileName.indexOf(ID_SEPARATOR) + 1, fileName.lastIndexOf(DOT_TXT));
    }
}
