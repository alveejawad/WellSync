package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidDailyLogException;

import java.util.regex.Pattern;

public abstract class SubstanceValidator {

    public static void validateSubstance(Substance drugs) throws InvalidDailyLogException {
        if (drugs == null)
            throw new InvalidDailyLogException("Medication details object undefined.");
        validateName(drugs.getName());
        validateQuantity(drugs.getQuantity());
    }
    private static void validateQuantity(int quantity) throws InvalidDailyLogException {
        if (quantity < 0)
            throw new InvalidDailyLogException("Invalid quantity specified.");
    }
    private static void validateName(String name) throws InvalidDailyLogException {
        DailyLogValidator.validateNonNullObject(name, "substance name");
        if (!Pattern.matches("[\\w -]{2,}", name))
            throw new InvalidDailyLogException("Invalid name specified.");
    }

}
