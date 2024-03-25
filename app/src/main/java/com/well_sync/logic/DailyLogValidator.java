package com.well_sync.logic;

import com.well_sync.R;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Medication;
import com.well_sync.objects.Substance;
import com.well_sync.objects.Symptom;

import java.util.Date;
import java.util.List;

/**
 * Utility class to validate fields in the DailyLog DSO.
 */
public abstract class DailyLogValidator {

    public static void validateLog(DailyLog log) throws InvalidDailyLogException {
        if (log == null)
            throw new InvalidDailyLogException("Daily log object undefined.");

        validateDate(log.getDate());
        validateMoodScore(log.getMoodScore());
        validateSleepHours(log.getSleepHours());

        validateMedicationList(log.getMedications());
        validateSymptomList(log.getSymptoms());
        validateSubstanceList(log.getSubstances());
    }

    private static void validateDate(Date date) throws InvalidDailyLogException {
        if (date == null)
            throw new InvalidDailyLogException("Daily log does not have a defined date.");

        if (date.after(new Date()))
            throw new InvalidDailyLogException("Daily log cannot be set to a future date.");
    }

    public static void validateMoodScore(int moodScore) throws InvalidDailyLogException {
        if (moodScore < 0 || moodScore > R.integer.max_mood_score)
            throw new InvalidDailyLogException("Invalid mood score; must be between 1 and 4 inclusive.");
    }

    public static void validateSleepHours(int sleepHours) throws InvalidDailyLogException {
        if (sleepHours < 0 || sleepHours > R.integer.max_sleep_hours)
            throw new InvalidDailyLogException("Invalid sleep amount; must be between 0 and 16 hours inclusive.");
    }

    public static void validateMedicationList(List<Medication> medicationList) throws InvalidDailyLogException {
        for (Medication m : medicationList) {
            validateMedication(m);
        }
    }
    public static void validateMedication(Medication medicine) throws InvalidDailyLogException {
        if (medicine == null)
            throw new InvalidDailyLogException("Medication details object undefined.");

        ValidationUtils.validateName(InvalidDailyLogException.class, medicine.getName());

        if (medicine.getQuantity() < 0 || medicine.getQuantity() > R.integer.max_med_quantity)
            throw new InvalidDailyLogException("Invalid quantity specified.");

        if (medicine.getDosage() < 0 || medicine.getDosage() > R.integer.max_med_dosage)
            throw new InvalidDailyLogException("Invalid dosage specified.");
    }

    public static void validateSymptomList(List<Symptom> symptomList) throws InvalidDailyLogException {
        for (Symptom s : symptomList) {
            validateSymptom(s);
        }
    }
    public static void validateSymptom(Symptom symptom) throws InvalidDailyLogException {
        if (symptom == null)
            throw new InvalidDailyLogException("Medication details object undefined.");

        ValidationUtils.validateName(InvalidDailyLogException.class, symptom.getName());
        if (symptom.getIntensity() < 0 || symptom.getIntensity() > R.integer.max_symptom_intensity)
            throw new InvalidDailyLogException("Invalid intensity specified.");
    }

    public static void validateSubstanceList(List<Substance> substanceList) throws InvalidDailyLogException {
        for (Substance s : substanceList) {
            validateSubstance(s);
        }
    }
    public static void validateSubstance(Substance substance) throws InvalidDailyLogException {
        if (substance == null)
            throw new InvalidDailyLogException("Substance details object undefined.");
        ValidationUtils.validateName(InvalidDailyLogException.class, substance.getName());

        if (substance.getQuantity() < 0 || substance.getQuantity() > R.integer.max_substance_quantity)
            throw new InvalidDailyLogException("Invalid quantity specified.");
    }
}
