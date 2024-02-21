package com.well_sync.logic.exceptions;

public class InvalidDailyLogException extends RuntimeException {
    public InvalidDailyLogException(String error) {
        super("Invalid data entered into log:\n" + error);
    }
}
