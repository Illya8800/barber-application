package com.barber.hopak.service.buffer;

import com.barber.hopak.util.StringUtils3C;

import java.io.File;
import java.util.Objects;

public class BufferedFileName {
    private final Long id;
    private final String imageName;
    private final String DOT_TXT = ".txt";
    private final String ID_SEPARATOR = "-";

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

    public Long getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    public String getFileNameRegex() {
        return StringUtils3C.join("^\\d{1,19}", ID_SEPARATOR, imageName, DOT_TXT, "$");
    }

    public Long getIdFromBufferedFileName(String fileName){
        return Long.valueOf(fileName.substring(0, fileName.indexOf(ID_SEPARATOR)));
    }
    public String getNameFromBufferedFileName(String fileName){
        return fileName.substring(fileName.indexOf(ID_SEPARATOR) + 1, fileName.lastIndexOf(DOT_TXT));
    }
}
