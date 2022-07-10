package com.example.mycli.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountConflict extends RuntimeException{
    public AccountConflict(String input) {
        super("Account with email " + input + " is already registered");
        log.info("Account with email " + input + " is already registered");
    }
}