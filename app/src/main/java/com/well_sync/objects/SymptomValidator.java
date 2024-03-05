package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidDailyLogException;

import java.util.regex.Pattern;

public abstract class SymptomValidator {
    public static void validateSymptom(Symptom symptoms) throws InvalidDailyLogException {
        if (symptoms == null)
            throw new InvalidDailyLogException("Medication details object undefined.");
        validateName(symptoms.getName());
        validateQuantity(symptoms.getIntensity());
    }
    private static void validateQuantity(int intensity) throws InvalidDailyLogException {
        if (intensity < 0 || intensity > 10)
            throw new InvalidDailyLogException("Invalid intensity specified.");
    }
    private static void validateName(String name) throws InvalidDailyLogException {
        DailyLogValidator.validateNonNullObject(name, "symptom name");
        if (!Pattern.matches("[\\w -]{2,}", name))
            throw new InvalidDailyLogException("Invalid name specified.");
    }
}
