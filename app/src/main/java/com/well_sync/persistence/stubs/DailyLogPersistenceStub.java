package com.well_sync.persistence.stubs;

import com.well_sync.objects.Patient;
import com.well_sync.objects.DailyLog;
import com.well_sync.persistence.IDailyLogPersistence;
import com.well_sync.persistence.IUserPersistence;

import java.util.*;

public class DailyLogPersistenceStub implements IDailyLogPersistence {

    private final Map<Patient, List<DailyLog>> dailyLogList;
    private IUserPersistence userPersistence;

    public DailyLogPersistenceStub() {
        dailyLogList = new HashMap<>();
        this.userPersistence = new UserPersistenceStub();

        // Default Sample Data
        Patient patient1 = userPersistence.getPatient("patient1@example.com");
        Patient patient2 = userPersistence.getPatient("patient2@example.com");

        List<DailyLog> dailyLogsPatient1 = new ArrayList<>();
        List<DailyLog> dailyLogsPatient2 = new ArrayList<>();

        DailyLog patient1Day1 = new DailyLog(new Date(2023, Calendar.JANUARY, 1), 7, 8, "Feeling good today");
        DailyLog patient1Day2 = new DailyLog(new Date(2023, Calendar.JANUARY, 2), 5, 7, "Had trouble sleeping");
        DailyLog patient2Day1 = new DailyLog(new Date(2023, Calendar.JANUARY, 1), 6, 7, "Normal day");

        patient1Day1.addSymptom("Headache", 5);
        patient1Day2.addSymptom("Fatigue", 4);
        patient2Day1.addSymptom("Sore throat", 8);

        patient1Day1.addMedication("Tylenol", 2);
        patient1Day2.addMedication("Melatonin", 1);
        patient2Day1.addMedication("Cough syrup", 3);

        patient1Day1.addSubstance("Alcohol", 2);
        patient1Day2.addSubstance("Caffiene", 2);
        patient2Day1.addSubstance("Caffiene", 2);

        dailyLogsPatient1.add(patient1Day1);
        dailyLogsPatient1.add(patient1Day2);
        dailyLogsPatient2.add(patient2Day1);

        dailyLogList.put(patient1, dailyLogsPatient1);
        dailyLogList.put(patient2, dailyLogsPatient2);
    }

    @Override
    public void setDailyLog(Patient patient, DailyLog dailyLog) {
        if (!dailyLogList.containsKey(patient)) {
            dailyLogList.put(patient, new ArrayList<>());
        }
        Objects.requireNonNull(dailyLogList.get(patient)).add(dailyLog);
    }

    @Override
    public DailyLog getDailyLog(Patient patient, Date date) {
        List<DailyLog> patientDailyLogs = dailyLogList.get(patient);
        if (patientDailyLogs != null) {
            for (DailyLog dailyLog : patientDailyLogs) {
                if (dailyLog.getDate().equals(date)) {
                    return dailyLog;
                }
            }
        }
        return null;
    }

    @Override
    public List<DailyLog> getAllDailyLogs(Patient patient) {
        return dailyLogList.getOrDefault(patient, new ArrayList<>());
    }
}
