package com.example.mycli.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class AuthenticationFailed extends RuntimeException{
    public AuthenticationFailed(String string) {
        super("Authentication failed: " + string);
        log.info("Authentication failed: " + string);
    }

}
