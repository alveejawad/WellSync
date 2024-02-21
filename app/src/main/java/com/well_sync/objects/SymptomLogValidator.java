package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidDailyLogException;

import java.util.List;

public class SymptomLogValidator extends DailyLogValidator {

    public static void validateSymptomLog(SymptomLog log) throws InvalidDailyLogException {
        DailyLogValidator.validateLog(log);
        validateSymptomList(log.getSymptoms());
    }

    public static void validateSymptomList(List<SymptomLog.Symptom> list) throws InvalidDailyLogException {
        list.forEach(SymptomLogValidator::validateSymptom);
    }

    public static void validateSymptom(SymptomLog.Symptom symptom) throws InvalidDailyLogException {
        if (symptom == null)
            throw new InvalidDailyLogException("Symptom object is not defined.");

        if (symptom.name == null || symptom.name.length() == 0)
            throw new InvalidDailyLogException("Symptom must have a defined name.");

        if (symptom.intensity < 0 || symptom.intensity > 10)
            throw new InvalidDailyLogException("Symptom intensity must be between 0 and 10, inclusive.");
    }
}
