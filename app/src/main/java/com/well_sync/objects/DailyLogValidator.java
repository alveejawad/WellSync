package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidDailyLogException;

import java.util.Date;

/**
 * Parent validator for any daily log object.
 */
public abstract class DailyLogValidator {

    public static void validateLog(IDailyLog log) throws InvalidDailyLogException {
        if (log == null)
            throw new InvalidDailyLogException("Daily log object undefined.");

        validateDate(log.getDate());
    }

    public static void validateDate(Date date) throws InvalidDailyLogException {
        if (date == null)
            throw new InvalidDailyLogException("Daily log does not have a defined date.");

        if (date.after(new Date()))
            throw new InvalidDailyLogException("Daily log cannot be set to a future date.");
    }
}
