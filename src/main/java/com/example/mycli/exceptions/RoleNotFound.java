package com.example.mycli.exceptions;

public class RoleNotFound extends RuntimeException{
    public RoleNotFound(Integer role) {
        super("Could not find role " + role);
    }
}
