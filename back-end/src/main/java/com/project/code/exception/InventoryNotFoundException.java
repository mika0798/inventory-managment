package com.project.code.exception;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(String message) {
        super(message);
    }

    public InventoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
