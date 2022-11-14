package com.example.bookexchange.exception;

public class FileNotSavedException extends RuntimeException {

    public FileNotSavedException() {
    }

    public FileNotSavedException(String message) {
        super(message);
    }
}
