package com.well_sync.logic.exceptions;

public class InvalidMoodLogException extends RuntimeException {
    public InvalidMoodLogException(String error) {
        super("Invalid data entered into mood log:\n" + error);
    }
}
