package com.example.mycli.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FolderInit extends RuntimeException{
    public FolderInit() {
        super("Failed to initialize upload folder");
        log.info("Failed to initialize upload folder");
    }
}
