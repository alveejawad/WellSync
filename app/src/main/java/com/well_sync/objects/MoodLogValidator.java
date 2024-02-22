package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidDailyLogException;

public class MoodLogValidator extends DailyLogValidator {

    public static void validateMoodLog(MoodLog log) throws InvalidDailyLogException {
        DailyLogValidator.validateLog(log);

        validateMoodScore(log.getMoodScore());
        validateSleepHours(log.getSleepHours());
        // no validation for notes; that field is optional (can be null)
    }

    public static void validateMoodScore(int moodScore) throws InvalidDailyLogException {
        if (moodScore < 1 || moodScore > 4)
            throw new InvalidDailyLogException("Invalid mood score; must be between 1 and 4 inclusive.");
    }

    public static void validateSleepHours(int sleepHours) throws InvalidDailyLogException {
        if (sleepHours < 0 || sleepHours > 16)
            throw new InvalidDailyLogException("Invalid sleep amount; must be between 0 and 16 hours inclusive.");
    }
}
