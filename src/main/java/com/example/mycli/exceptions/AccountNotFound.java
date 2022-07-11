package com.example.mycli.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountNotFound extends RuntimeException{
    public AccountNotFound(String id) {
        super("Could not find " + id);
        log.info("Could not find " + id);
    }
}
