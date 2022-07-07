package com.example.mycli.exceptions;

public class AccountCreated extends RuntimeException{
    public AccountCreated(String input) {
        super("Account " + input + " was successfully registered");
    }
}