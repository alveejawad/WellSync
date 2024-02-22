package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.MoodLog;
import com.well_sync.objects.MoodLogValidator;
import com.well_sync.objects.Patient;
import com.well_sync.persistence.IMoodLogPersistence;

import java.util.Date;

public class MoodLogHandler {

    private final IMoodLogPersistence persistLog;

    public MoodLogHandler() {
        persistLog = Services.getMoodLogPersistence();
    }

    public MoodLogHandler(IMoodLogPersistence persistence){
        persistLog = persistence;
    }

    public boolean setMoodLog(Patient patient, MoodLog moodLog) {
        try {
            MoodLogValidator.validateMoodLog(moodLog);
            persistLog.setMoodLog(patient, moodLog);
        } catch (InvalidDailyLogException e) {
            return false;
        }

        return true;
    }

    public MoodLog getMoodLog(Patient patient, Date date) {
        return persistLog.getMoodLog(patient, date);
    }
}
