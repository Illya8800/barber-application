package com.barber.hopak.config;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "buffer")
@Getter
@Log4j2
public class BufferPathConfig {
    private String path;

    public void setPath(String path) {
        if (path.trim().isEmpty()) throw new RuntimeException("Empty path to buffer. You should set it in application.properties");
        log.info("Current path to buffer: {}", path);
        this.path = path;
    }
}