package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidPatientException;

import java.util.regex.Pattern;

public class PatientValidator {

    public static void validatePatient(Patient patient) throws InvalidPatientException {
        if (patient == null)
            throw new InvalidPatientException("Patient details object undefined.");

        UserCredentialsValidator.validateEmail(patient.getEmail());
        validateName(patient.getFirstName());
        validateName(patient.getLastName());
        validateAge(patient.getAge());
        validateNonNullObject(patient.getBloodType(), "blood type");
        validateNonNullObject(patient.getSex(), "sex");
    }

    /**
     * Valid name uses 2 or more alphabetic characters; nothing else.
     */
    public static void validateName(String name) throws InvalidPatientException {
        validateNonNullObject(name, "patient name");
        if (!Pattern.matches("[\\w -]{2,}", name))
            throw new InvalidPatientException("Invalid name specified.");
    }

    public static void validateAge(int age) throws InvalidPatientException {
        // https://en.wikipedia.org/wiki/Jeanne_Calment
        if (age < 0 || age > 122)
            throw new InvalidPatientException("Invalid age specified.");
    }

    // Helper method to validate object references that shouldn't be null, e.g. strings and enums.
    // The `subject` field is just to format the exception message with the correct field.
    private static void validateNonNullObject(Object object, String subject) throws InvalidPatientException {
        if (object == null)
            throw new InvalidPatientException("No " + subject + " specified.");
    }

}
