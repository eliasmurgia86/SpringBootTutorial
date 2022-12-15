package com.elias.springboottutorial.exception;

public class ConstraintViolationException extends RuntimeException {

    public ConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstraintViolationException() {}
}
