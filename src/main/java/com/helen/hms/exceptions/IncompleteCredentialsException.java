package com.helen.hms.exceptions;

public class IncompleteCredentialsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public IncompleteCredentialsException(String message) {
        super(message);
    }
}
