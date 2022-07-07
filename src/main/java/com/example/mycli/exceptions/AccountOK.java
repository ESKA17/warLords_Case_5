package com.example.mycli.exceptions;

public class AccountOK extends RuntimeException{
    public AccountOK() {
        super("Successful authentication");
    }
}