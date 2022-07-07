package com.example.mycli.exceptions;

public class AccountBadRequest extends RuntimeException{
    public AccountBadRequest(String input) {
        super("Wrong input: " + input);
    }
}