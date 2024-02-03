package com.well_sync.logic.exceptions;

public class NonPositiveCookingTimeException extends InvalidCookingTimeException{
    public NonPositiveCookingTimeException(String error) {
        super("The cooking time is a non-positive number:\n" + error);
    }
}
