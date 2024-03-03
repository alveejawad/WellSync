package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidPatientException;
import com.well_sync.logic.exceptions.InvalidDailyLogException;

import java.util.regex.Pattern;

public class MedicationValidator {
    public static void validateMedication (Medication Medicine) {
        if (Medicine == null)
            throw new InvalidDailyLogException("Medication details object undefined.");
        validateName(Medicine.getName());
        validateQuantity(Medicine.getQuantity());
    }
    public static void validateQuantity(int quantity) throws InvalidDailyLogException {
        // https://en.wikipedia.org/wiki/Jeanne_Calment
        if (quantity < 0 || quantity > 5)
            throw new InvalidDailyLogException("Invalid quantity specified.");
    }
    public static void validateName(String name) throws InvalidDailyLogException {
        validateNonNullObject(name, "medication name");
        if (!Pattern.matches("[\\w -]{2,}", name))
            throw new InvalidDailyLogException("Invalid name specified.");
    }
    private static void validateNonNullObject(Object object, String subject) throws InvalidDailyLogException {
        if (object == null)
            throw new InvalidDailyLogException("No " + subject + " specified.");
    }
}
