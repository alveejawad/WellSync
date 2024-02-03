package com.well_sync.logic.exceptions;

public class InvalidTagException extends RuntimeException {
    public InvalidTagException(String error) {
        super("Unable to access Tag data:\n" + error);
    }
}
