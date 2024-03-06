package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidCredentialsException;
import com.well_sync.logic.exceptions.InvalidDoctorException;

import java.util.regex.Pattern;

public class DoctorValidator {
    public static void validateDoctor(Doctor doctor) throws InvalidDoctorException {
        if (doctor == null)
            throw new InvalidDoctorException("Doctor details object undefined.");

        try {
            UserCredentialsValidator.validateEmail(doctor.getEmail());
        } catch (InvalidCredentialsException e) {
            throw new InvalidDoctorException(e.getMessage());
        }

        validateName(doctor.getFirstName());
        validateName(doctor.getLastName());
    }

    /**
     * Valid name uses 2 or more alphabetic characters; nothing else.
     */
    public static void validateName(String name) throws InvalidDoctorException {
        validateNonNullObject(name, "doctor name");
        if (!Pattern.matches("[\\w -]{2,}", name))
            throw new InvalidDoctorException("Invalid name specified.");
    }

    // Helper method to validate object references that shouldn't be null, e.g. strings and enums.
    // The `subject` field is just to format the exception message with the correct field.
    public static void validateNonNullObject(Object object, String subject) throws InvalidDoctorException {
        if (object == null)
            throw new InvalidDoctorException("No " + subject + " specified.");
    }
}
