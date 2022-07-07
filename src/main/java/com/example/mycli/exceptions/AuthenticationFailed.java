package com.example.mycli.exceptions;

public class AuthenticationFailed extends RuntimeException{
    public AuthenticationFailed() {
        super("Authentication failed");    
    }

}
