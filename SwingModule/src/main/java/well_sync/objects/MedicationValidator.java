package well_sync.objects;

import well_sync.logic.exceptions.InvalidDailyLogException;

import java.util.regex.Pattern;

public abstract class MedicationValidator {
    public static void validateMedication(Medication medicine) throws InvalidDailyLogException {
        if (medicine == null)
            throw new InvalidDailyLogException("Medication details object undefined.");
        validateName(medicine.getName());
        validateQuantity(medicine.getQuantity(), medicine.maxQuantity);
    }
    private static void validateQuantity(int quantity, int maxQuantity) throws InvalidDailyLogException {
        if (quantity < 0 || quantity > maxQuantity)
            throw new InvalidDailyLogException("Invalid quantity specified.");
    }
    private static void validateName(String name) throws InvalidDailyLogException {
        DailyLogValidator.validateNonNullObject(name, "medication name");
        if (!Pattern.matches("[\\w -]{2,}", name))
            throw new InvalidDailyLogException("Invalid name specified.");
    }
}
