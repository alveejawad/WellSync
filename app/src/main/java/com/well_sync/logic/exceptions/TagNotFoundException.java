package com.well_sync.logic.exceptions;

public class TagNotFoundException extends RuntimeException{
    public TagNotFoundException(String error) {
        super("The tag is not found:\n" + error);
    }
}
