package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidMoodLogException;

import java.util.Date;

public class MoodLogValidator {

    public static void validateMoodLog(MoodLog log) throws InvalidMoodLogException {
        if (log == null)
            throw new InvalidMoodLogException("Mood log object undefined.");

        validateMoodScore(log.getMoodScore());
        validateSleepHours(log.getSleepHours());
        validateDate(log.getDate());
        // no validation for notes; that field is optional (can be null)
    }

    public static void validateMoodScore(int moodScore) throws InvalidMoodLogException {
        if (moodScore < 1 || moodScore > 4)
            throw new InvalidMoodLogException("Invalid mood score; must be between 1 and 4 inclusive.");
    }

    public static void validateSleepHours(int sleepHours) throws InvalidMoodLogException {
        if (sleepHours < 0 || sleepHours > 16)
            throw new InvalidMoodLogException("Invalid sleep amount; must be between 0 and 16 hours inclusive.");
    }

    public static void validateDate(Date date) throws InvalidMoodLogException {
        if (date == null)
            throw new InvalidMoodLogException("Mood log does not have a defined date.");

        if (date.after(new Date()))
            throw new InvalidMoodLogException("Mood log cannot be set to a future date.");
    }
}
