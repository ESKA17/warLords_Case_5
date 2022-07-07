package com.example.mycli.exceptions;

public class FolderInit extends RuntimeException{
    public FolderInit() {
        super("Failed to initialize upload folder");
    }
}
