package com.well_sync.logic.exceptions;

public class InvalidPatientException extends RuntimeException {
    public InvalidPatientException(String error) {
        super("Invalid patient details:\n" + error);
    }
}
