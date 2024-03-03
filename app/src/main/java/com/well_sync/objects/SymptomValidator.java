package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidDailyLogException;

import java.util.regex.Pattern;

public class SymptomValidator {
    public static void validateSymptom (Symptom symptoms) {
        if (symptoms == null)
            throw new InvalidDailyLogException("Medication details object undefined.");
        validateName(symptoms.getName());
        validateQuantity(symptoms.getIntensity());
    }
    public static void validateQuantity(int intensity) throws InvalidDailyLogException {
        // https://en.wikipedia.org/wiki/Jeanne_Calment
        if (intensity < 0 || intensity > 10)
            throw new InvalidDailyLogException("Invalid intensity specified.");
    }
    public static void validateName(String name) throws InvalidDailyLogException {
        validateNonNullObject(name, "symptom name");
        if (!Pattern.matches("[\\w -]{2,}", name))
            throw new InvalidDailyLogException("Invalid name specified.");
    }
    private static void validateNonNullObject(Object object, String subject) throws InvalidDailyLogException {
        if (object == null)
            throw new InvalidDailyLogException("No " + subject + " specified.");
    }
}
