package com.barber.hopak.buffer.impl;

import com.barber.hopak.util.buffer.BufferTestUtils;
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
    private final BufferTestUtils bufferTestUtils;
    private final BufferStateImpl bufferState;

    @Autowired
    FileSearcherImplTest(FileSearcherImpl fileSearcher, BufferTestUtils bufferTestUtils, BufferStateImpl bufferState) {
        this.fileSearcher = fileSearcher;
        this.bufferTestUtils = bufferTestUtils;
        this.bufferState = bufferState;
    }

    @BeforeEach
    void initBuffer() {
        bufferState.init();
    }

    @AfterEach
    void destroyBuffer() {
        bufferState.destroy();
    }

    @Test
    void getFileByName_thenFind() {
        bufferTestUtils.createTestFile();
        Optional<File> fileByName = fileSearcher.getFileByName(BufferTestUtils.EXISTING_FILE_NAME);
        assertThat(fileByName).isPresent();
        boolean isFileDeleted = bufferTestUtils.deleteTestFile(fileByName.get().getName());
        assertThat(isFileDeleted).isTrue();
    }

    @Test
    void getFileByName_thenFileNotFound() {
        Optional<File> fileByName = fileSearcher.getFileByName(BufferTestUtils.UNEXISTING_FILE_NAME);
        assertThat(fileByName).isEmpty();
    }

    @Test
    void getFileById_thenFind() {
        bufferTestUtils.createTestFile();
        Optional<File> fileById = fileSearcher.getFileById(BufferTestUtils.EXISTING_FILE_ID);
        assertThat(fileById).isPresent();
        boolean isFileDeleted = bufferTestUtils.deleteTestFile(fileById.get().getName());
        assertThat(isFileDeleted).isTrue();
    }

    @Test
    void getFileById_thenFileNotFound() {
        Optional<File> fileById = fileSearcher.getFileById(BufferTestUtils.UNEXISTING_FILE_ID);
        assertThat(fileById).isEmpty();
    }

    @Test
    void getBufferPath_thenMustBeNotNull() {
        String bufferPath = bufferState.getBufferPath();
        assertThat(bufferPath).isNotNull();
    }
}