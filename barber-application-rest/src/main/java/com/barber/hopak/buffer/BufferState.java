package com.barber.hopak.buffer;

public interface BufferState {
    void init();
    void destroy();
    String getBufferPath();
}
