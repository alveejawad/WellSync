package com.well_sync.logic;

import com.well_sync.logic.exceptions.InvalidNotesException;
import com.well_sync.logic.exceptions.InvalidPatientException;
import com.well_sync.objects.Patient;

/**
 * Package-private utility class to validate fields in the Patient DSO.
 */
abstract class PatientValidator {
    public static void validatePatient(Patient patient) throws InvalidPatientException {
        if (patient == null)
            throw new InvalidPatientException("Patient details object undefined.");

        ValidationUtils.validateEmail(InvalidPatientException.class, patient.getEmail());
        ValidationUtils.validateName(InvalidPatientException.class, patient.getFirstName());
        ValidationUtils.validateName(InvalidPatientException.class, patient.getLastName());

        validateAge(patient.getAge(), patient.MAX_AGE);
        validateDoctorNotes(patient.getDoctorNotes(), patient.MAX_NOTES_LENGTH);

        if (patient.getBloodType() == null)
            throw new InvalidPatientException("No blood type specified.");

        if (patient.getSex() == null)
            throw new InvalidPatientException("No sex specified.");
    }


    private static void validateAge(int age, int maxAge) throws InvalidPatientException {
        if (age < 0 || age > maxAge)
            throw new InvalidPatientException("Invalid age specified.");
    }

    private static void validateDoctorNotes(String notes, int maxLen) throws InvalidNotesException {
        if (notes == null || notes.length() > maxLen) {
            throw new InvalidNotesException("Exceeded word limit.");
        }

    }
}
