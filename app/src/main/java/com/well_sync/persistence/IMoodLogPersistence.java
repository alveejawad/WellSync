package com.well_sync.persistence;

import com.well_sync.objects.Patient;
import com.well_sync.objects.MoodLog;

import java.util.*;

public interface IMoodLogPersistence {
    void setMoodLog(Patient patient, MoodLog moodLog);

    MoodLog getMoodLog(Patient patient, Date date);
}
