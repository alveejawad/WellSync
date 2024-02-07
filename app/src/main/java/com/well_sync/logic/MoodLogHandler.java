package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.objects.MoodLog;
import com.well_sync.objects.Patient;
import com.well_sync.persistence.MoodLogPersistence;

import java.util.Date;

public class MoodLogHandler {

    private final MoodLogPersistence persistLog;

    public MoodLogHandler() {
        persistLog = Services.getMoodLogPersistence();
    }

    public MoodLogHandler(MoodLogPersistence persistence){
        persistLog = persistence;
    }

    public void setMoodLog(Patient patient, MoodLog moodLog) {
        persistLog.setMoodLog(patient, moodLog);
    }

    public MoodLog getMoodLog(Patient patient, Date date) {
        return persistLog.getMoodLog(patient, date);
    }
}
