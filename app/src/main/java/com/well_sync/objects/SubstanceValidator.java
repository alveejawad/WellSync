package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidDailyLogException;

import java.util.regex.Pattern;

public abstract class SubstanceValidator {

    public static void validateSubstance(Substance substance) throws InvalidDailyLogException {
        if (substance == null)
            throw new InvalidDailyLogException("Medication details object undefined.");
        validateName(substance.getName());
        validateQuantity(substance.getQuantity(), substance.maxQuantity);
    }
    private static void validateQuantity(int quantity, int maxQuantity) throws InvalidDailyLogException {
        if (quantity < 0 || quantity > maxQuantity)
            throw new InvalidDailyLogException("Invalid quantity specified.");
    }
    private static void validateName(String name) throws InvalidDailyLogException {
        DailyLogValidator.validateNonNullObject(name, "substance name");
        if (!Pattern.matches("[\\w -]{2,}", name))
            throw new InvalidDailyLogException("Invalid name specified.");
    }

}
