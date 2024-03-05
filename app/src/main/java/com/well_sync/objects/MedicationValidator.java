package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidDailyLogException;

import java.util.regex.Pattern;

public abstract class MedicationValidator {
    public static void validateMedication (Medication Medicine) throws InvalidDailyLogException {
        if (Medicine == null)
            throw new InvalidDailyLogException("Medication details object undefined.");
        validateName(Medicine.getName());
        validateQuantity(Medicine.getQuantity());
    }
    private static void validateQuantity(int quantity) throws InvalidDailyLogException {
        if (quantity < 0 || quantity > 5)
            throw new InvalidDailyLogException("Invalid quantity specified.");
    }
    private static void validateName(String name) throws InvalidDailyLogException {
        DailyLogValidator.validateNonNullObject(name, "medication name");
        if (!Pattern.matches("[\\w -]{2,}", name))
            throw new InvalidDailyLogException("Invalid name specified.");
    }
}
