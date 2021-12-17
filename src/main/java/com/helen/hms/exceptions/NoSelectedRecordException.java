package com.helen.hms.exceptions;

public class NoSelectedRecordException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NoSelectedRecordException(String message) {
        super(message);
    }
}
