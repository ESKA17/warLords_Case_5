package com.example.mycli.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordFailed extends RuntimeException{
    public PasswordFailed() {
        super("bad request: password");
        log.info("bad request: password");
    }

}