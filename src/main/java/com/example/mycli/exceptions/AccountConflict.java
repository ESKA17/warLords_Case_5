package com.example.mycli.exceptions;

public class AccountConflict extends RuntimeException{
    public AccountConflict(String input) {
        super("Account " + input + " is already registered");
    }
}