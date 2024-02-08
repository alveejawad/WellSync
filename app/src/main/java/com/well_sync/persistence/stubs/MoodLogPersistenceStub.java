package com.well_sync.persistence.stubs;

import com.well_sync.objects.Patient;
import com.well_sync.objects.MoodLog;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.MoodLogPersistence;
import com.well_sync.persistence.UserPersistence;

import java.util.*;

public class MoodLogPersistenceStub implements MoodLogPersistence {

    private final Map<Patient, List<MoodLog>> moodLogList;
    private UserPersistence userPersistence;

    public MoodLogPersistenceStub() {
        moodLogList = new HashMap<>();
        userPersistence = new UserPersistenceStub();

        Patient patient1 = userPersistence.getPatient(new UserCredentials("test123@umanitoba.ca", "test123"));
        List<MoodLog> moodLogsPatient1 = new ArrayList<>();
        moodLogsPatient1.add(new MoodLog(new Date(124, Calendar.FEBRUARY, 7), 3, 6, "Anxious"));
        moodLogList.put(patient1, moodLogsPatient1);

        Patient patient2 = userPersistence.getPatient(new UserCredentials("muhammad@umanitoba.ca", "gossipzilla"));
        List<MoodLog> moodLogsPatient2 = new ArrayList<>();
        moodLogsPatient1.add(new MoodLog(new Date(124, Calendar.JANUARY, 31), 4, 7, "Sad"));
        moodLogList.put(patient2, moodLogsPatient2);
    }

    @Override
    public void setMoodLog(Patient patient, MoodLog moodLog) {
        if (!moodLogList.containsKey(patient)) {
            moodLogList.put(patient, new ArrayList<>());
        }
        moodLogList.get(patient).add(moodLog);
    }

    @Override
    public MoodLog getMoodLog(Patient patient, Date date) {
        List<MoodLog> patientMoodLogs = moodLogList.get(patient);
        if (patientMoodLogs != null) {
            for (MoodLog moodLog : patientMoodLogs) {
                if (moodLog.getDate().equals(date)) {
                    return moodLog;
                }
            }
        }
        return null;
    }
}
