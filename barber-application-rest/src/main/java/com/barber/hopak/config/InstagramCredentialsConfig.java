package com.barber.hopak.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "instagram")
@Getter
@Setter
public class InstagramCredentialsConfig {
    private String username;
    private String password;
}
