package com.well_sync.logic.exceptions;

public class InvalidNotesException extends InvalidPatientException {
    public InvalidNotesException (String error) {
        super("Invalid doctor notes in Patient:\n" + error);
    }
}
