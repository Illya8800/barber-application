package com.barber.hopak.service.api;

import com.barber.hopak.config.InstagramCredentialsConfig;
import com.barber.hopak.exception.instagram.InstagramCredentialException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@Service
public class InstagramApiService {
    public static final String OK = "ok";
    private final InstagramCredentialsConfig instagramCredentials;
    private  Instagram4j myAccount;

    @PostConstruct
    private void init(){
        myAccount = loginMyAccount();
    }

    public boolean isUserExist(String username) {
        log.info("Searching instagram account");
        try {
            InstagramSearchUsernameResult foundAccount = myAccount.sendRequest(new InstagramSearchUsernameRequest(username));
            return foundAccount.getStatus().equals(OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Instagram4j loginMyAccount() {
        log.info("Login instagram account");
        Instagram4j instagram = login(instagramCredentials.getUsername(), instagramCredentials.getPassword());
        try {
            instagram.login();
            return instagram;
        } catch (IOException e) {
            log.error("Login in account wasn't successful. Check your credentials in application properties");
            throw new InstagramCredentialException(e);
        }
    }

    private Instagram4j login(String username, String password) {
        credentialsCheck();
        Instagram4j instagram = Instagram4j.builder()
                .username(username)
                .password(password).build();
        instagram.setup();
        return instagram;
    }

    private void credentialsCheck() {
        if (StringUtils.isEmpty(instagramCredentials.getUsername().trim()))
            throw new InstagramCredentialException("The instagram account username is empty. You must set it in application.properties");
        if (StringUtils.isEmpty(instagramCredentials.getPassword().trim()))
            throw new InstagramCredentialException("The instagram account password is empty. You must set it in application.properties");
    }
}
