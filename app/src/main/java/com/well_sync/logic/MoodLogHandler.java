package com.well_sync.logic;

import com.well_sync.objects.MoodLog;
import com.well_sync.objects.Patient;
import com.well_sync.persistence.MoodLogPersistence;

import java.util.Date;

public class MoodLogHandler {

    private MoodLogPersistence persistLog;

    public MoodLogHandler() {
        // TODO
    }

    public void setMoodLog(Patient patient, MoodLog moodLog) {
        this.persistLog.setMoodLog(patient, moodLog);
        return;
    }

    public MoodLog getMoodLog(Patient patient, Date date) {
        return this.persistLog.getMoodLog(patient, date);
    }
}
