package com.well_sync.persistence.stubs;

import com.well_sync.objects.Patient;
import com.well_sync.objects.MoodLog;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.IMoodLogPersistence;
import com.well_sync.persistence.IUserPersistence;

import java.util.*;

public class MoodLogPersistenceStub implements IMoodLogPersistence {

    private final Map<Patient, List<MoodLog>> moodLogList;
    private IUserPersistence userPersistence;

    public MoodLogPersistenceStub() {
        moodLogList = new HashMap<>();
        this.userPersistence = new UserPersistenceStub();

        // Default Sample Data
        Patient patient1 = userPersistence.getPatient("patient1@example.com");
        Patient patient2 = userPersistence.getPatient("patient2@example.com");

        List<MoodLog> moodLogsPatient1 = new ArrayList<>();
        List<MoodLog> moodLogsPatient2 = new ArrayList<>();

        MoodLog patient1Day1 = new MoodLog(new Date(2023, Calendar.JANUARY, 1), 7, 8, "Feeling good today");
        MoodLog patient1Day2 = new MoodLog(new Date(2023, Calendar.JANUARY, 2), 5, 7, "Had trouble sleeping");
        MoodLog patient2Day1 = new MoodLog(new Date(2023, Calendar.JANUARY, 1), 6, 7, "Normal day");

        patient1Day1.addSymptom("Headache", 5);
        patient1Day2.addSymptom("Fatigue", 4);
        patient2Day1.addSymptom("Sore throat", 8);

        patient1Day1.addMedication("Tylenol", 2);
        patient1Day2.addMedication("Melatonin", 1);
        patient2Day1.addMedication("Cough syrup", 3);

        patient1Day1.addSubstance("Alcohol", 2);
        patient1Day2.addSubstance("Caffiene", 2);
        patient2Day1.addSubstance("Caffiene", 2);

        moodLogsPatient1.add(patient1Day1);
        moodLogsPatient1.add(patient1Day2);
        moodLogsPatient2.add(patient2Day1);

        moodLogList.put(patient1, moodLogsPatient1);
        moodLogList.put(patient2, moodLogsPatient2);
    }

    @Override
    public void setMoodLog(Patient patient, MoodLog moodLog) {
        if (!moodLogList.containsKey(patient)) {
            moodLogList.put(patient, new ArrayList<>());
        }
        Objects.requireNonNull(moodLogList.get(patient)).add(moodLog);
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

    @Override
    public List<MoodLog> getAllMoodLogs(Patient patient) {
        return moodLogList.getOrDefault(patient, new ArrayList<>());
    }
}
