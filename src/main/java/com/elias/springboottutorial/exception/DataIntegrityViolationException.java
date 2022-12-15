package com.elias.springboottutorial.exception;

public class DataIntegrityViolationException extends RuntimeException {

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIntegrityViolationException() {}
}
