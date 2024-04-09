package org.softeng.assignment.logic.exceptions;

public class InvalidPatientException extends InvalidDataException {
    public InvalidPatientException(String error) {
        super("Invalid patient details:\n" + error);
    }
}
