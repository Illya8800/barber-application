package com.barber.hopak.buffer.impl;

import com.barber.hopak.buffer.BufferState;
import com.barber.hopak.config.BufferPathConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BufferStateImplTest {
    
    private final BufferState bufferState;
    private final BufferPathConfig bufferPathConfig;

    @Autowired
    BufferStateImplTest(BufferState bufferState, BufferPathConfig bufferPathConfig) {
        this.bufferState = bufferState;
        this.bufferPathConfig = bufferPathConfig;
    }

    @Test
    void init() {
        bufferState.init();
        File bufferFolder = new File(bufferPathConfig.getPath());
        assertThat(bufferFolder.exists()).isTrue();
    }

    @Test
    void destroy() {
        bufferState.init();
        File createdBufferFolder = new File(bufferPathConfig.getPath());
        assertThat(createdBufferFolder.exists()).isTrue();
        bufferState.destroy();
        File destroyedBufferFolder = new File(bufferPathConfig.getPath());
        assertThat(destroyedBufferFolder.exists()).isFalse();
    }

    @Test
    void getBufferPath() {
        String bufferPath = bufferState.getBufferPath();
        String expectedBufferPath = bufferPathConfig.getPath();
        assertThat(bufferPath).isNotNull();
        assertThat(bufferPath).isEqualTo(expectedBufferPath);
    }
}