package com.well_sync.logic.exceptions;

public class InvalidNotesException extends InvalidDoctorException{
    public InvalidNotesException (String error) {
        super("Invalid Doctor details:\n" + error);
    }
}
