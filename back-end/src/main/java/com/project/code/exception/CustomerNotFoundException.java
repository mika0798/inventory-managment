package com.project.code.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNotFoundException(Throwable cause) {
        super(cause);
    }
}
