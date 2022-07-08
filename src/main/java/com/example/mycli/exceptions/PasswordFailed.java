package com.example.mycli.exceptions;

public class PasswordFailed extends RuntimeException{
    public PasswordFailed() {
        super("Please check your password");
    }
}