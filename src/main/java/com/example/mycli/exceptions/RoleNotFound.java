package com.example.mycli.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleNotFound extends RuntimeException{
    public RoleNotFound(Integer role) {
        super("Could not find role " + role);
        log.info("Could not find role " + role);
    }
}
