package com.project.code.exception;

public class StoreNotFoundException extends RuntimeException {

    public StoreNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreNotFoundException(String message) {
        super(message);
    }

    public StoreNotFoundException(Throwable cause) {
        super(cause);
    }
}