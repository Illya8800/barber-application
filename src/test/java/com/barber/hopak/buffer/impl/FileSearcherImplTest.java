package com.barber.hopak.buffer.impl;

import com.barber.hopak.util.buffer.BufferUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FileSearcherImplTest {

    private final FileSearcherImpl fileSearcher;
    private final BufferUtils bufferUtils;

    @Autowired
    FileSearcherImplTest(FileSearcherImpl fileSearcher, BufferUtils bufferUtils) {
        this.fileSearcher = fileSearcher;
        this.bufferUtils = bufferUtils;
    }

    @BeforeEach
    void initBuffer() {
        bufferUtils.initBuffer();
    }

    @AfterEach
    void destroyBuffer() {
        bufferUtils.destroyBuffer();
    }

    @Test
    void getFileByName_thenFind() {
        bufferUtils.createTestFile();
        Optional<File> fileByName = fileSearcher.getFileByName(BufferUtils.EXISTING_FILE_NAME);
        assertThat(fileByName).isPresent();
        boolean isFileDeleted = bufferUtils.deleteTestFile(fileByName.get().getName());
        assertThat(isFileDeleted).isTrue();
    }

    @Test
    void getFileByName_thenFileNotFound() {
        Optional<File> fileByName = fileSearcher.getFileByName(BufferUtils.UNEXISTING_FILE_NAME);
        assertThat(fileByName).isEmpty();
    }

    @Test
    void getFileById_thenFind() {
        bufferUtils.createTestFile();
        Optional<File> fileById = fileSearcher.getFileById(BufferUtils.EXISTING_FILE_ID);
        assertThat(fileById).isPresent();
        boolean isFileDeleted = bufferUtils.deleteTestFile(fileById.get().getName());
        assertThat(isFileDeleted).isTrue();
    }

    @Test
    void getFileById_thenFileNotFound() {
        Optional<File> fileById = fileSearcher.getFileById(BufferUtils.UNEXISTING_FILE_ID);
        assertThat(fileById).isEmpty();
    }

    @Test
    void getBufferPath_thenMustBeNotNull() {
        String bufferPath = fileSearcher.getBufferPath();
        assertThat(bufferPath).isNotNull();
    }
}