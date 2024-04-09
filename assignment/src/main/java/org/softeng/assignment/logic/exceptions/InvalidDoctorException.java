package org.softeng.assignment.logic.exceptions;

public class InvalidDoctorException extends InvalidDataException {
        public InvalidDoctorException(String error) {
            super("Invalid doctor details:\n" + error);
        }
}
