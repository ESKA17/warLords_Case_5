package com.example.mycli.exceptions;

public class AccountNotFound extends RuntimeException{
    public AccountNotFound(String id) {
        super("Could not find account " + id);
    }
}
