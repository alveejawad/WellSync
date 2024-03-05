package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidDailyLogException;

import java.util.Date;

/**
 * Parent validator for any daily log object.
 */
public abstract class DailyLogValidator {

    public static void validateLog(DailyLog log) throws InvalidDailyLogException {
        if (log == null)
            throw new InvalidDailyLogException("Daily log object undefined.");

        validateDate(log.getDate());
        validateMoodScore(log.getMoodScore());
        validateSleepHours(log.getSleepHours());
    }

    private static void validateDate(Date date) throws InvalidDailyLogException {
        if (date == null)
            throw new InvalidDailyLogException("Daily log does not have a defined date.");

        if (date.after(new Date()))
            throw new InvalidDailyLogException("Daily log cannot be set to a future date.");
    }

    private static void validateMoodScore(int moodScore) throws InvalidDailyLogException {
        if (moodScore < 1 || moodScore > 4)
            throw new InvalidDailyLogException("Invalid mood score; must be between 1 and 4 inclusive.");
    }

    private static void validateSleepHours(int sleepHours) throws InvalidDailyLogException {
        if (sleepHours < 0 || sleepHours > 16)
            throw new InvalidDailyLogException("Invalid sleep amount; must be between 0 and 16 hours inclusive.");
    }

    public static void validateNonNullObject(Object object, String subject) throws InvalidDailyLogException {
        if (object == null)
            throw new InvalidDailyLogException("No " + subject + " specified.");
    }
}
