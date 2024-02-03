package com.well_sync.logic.exceptions;

public class InvalidRecipeException extends RuntimeException {
    public InvalidRecipeException(String error) {
        super("Unable to access Recipe data:\n" + error);
    }
}
