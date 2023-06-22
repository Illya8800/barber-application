package com.barber.hopak.org.springframework.web.multipart.custom;

import com.barber.hopak.util.StringUtils3C;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

@Log4j2
@EqualsAndHashCode
@ToString
public class MultipartFileWithoutPath implements MultipartFile {
    private final String name;
    private final String contentType;
    private final byte[] content;
    private static final String IMAGE_MEDIA_TYPE = "image/";
    private static final String DOT = ".";

    public MultipartFileWithoutPath(String name, byte[] content) {
        this.name = name;
        this.contentType = StringUtils3C.join(IMAGE_MEDIA_TYPE, name.substring(name.lastIndexOf(DOT) + 1));
        this.content = content;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return name;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public boolean isEmpty() {
        return this.content.length == 0;
    }

    @Override
    public long getSize() {
        return this.content.length;
    }

    @Override
    public byte[] getBytes() {
        return this.content;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(content);
    }

    @Deprecated(since = "This implementation doesn't support this method, because the file doesn't save on the server")
    @Override
    public Resource getResource() {
        return null;
    }

    @Deprecated(since = "This implementation doesn't support this method, because the file doesn't save on the server")
    @Override
    public void transferTo(Path dest) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    @Deprecated(since = "This implementation doesn't support this method, because the file doesn't save on the server")
    public void transferTo(File dest) {
        throw new UnsupportedOperationException("Operation not supported");
    }

}