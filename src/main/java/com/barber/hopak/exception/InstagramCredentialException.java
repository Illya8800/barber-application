package com.barber.hopak.exception;

import java.io.IOException;

public class InstagramCredentialException extends RuntimeException {
    public InstagramCredentialException(String s) {
        super(s);
    }

    public InstagramCredentialException(IOException e) {
        super(e);
    }
}
