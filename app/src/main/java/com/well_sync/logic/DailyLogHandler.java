package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;
import com.well_sync.objects.Symptom;
import com.well_sync.persistence.IDailyLogPersistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DailyLogHandler implements IDailyLogHandler {

    private final IDailyLogPersistence persistLog;

    public DailyLogHandler() {
        persistLog = Services.getDailyLogPersistence(true);
    }

    public DailyLogHandler(IDailyLogPersistence persistence){
        persistLog = persistence;
    }

    public void setDailyLog(Patient patient, DailyLog dailyLog) throws InvalidDailyLogException {
        DailyLogValidator.validateLog(dailyLog);
        persistLog.setDailyLog(patient, dailyLog);
    }
    public void setMedication(Patient patient, DailyLog dailyLog) throws InvalidDailyLogException {
        DailyLogValidator.validateMedicationList(dailyLog.getMedications());
        persistLog.setMedication(patient, dailyLog);
    }

    public void setSubstances(Patient patient, DailyLog dailyLog) throws InvalidDailyLogException {
        DailyLogValidator.validateSubstanceList(dailyLog.getSubstances());
        persistLog.setSubstances(patient, dailyLog);
    }
    public void setSymptoms(Patient patient, DailyLog dailyLog) throws InvalidDailyLogException {
        DailyLogValidator.validateSymptomList(dailyLog.getSymptoms());
        persistLog.setSymptoms(patient, dailyLog);
    }

    public DailyLog getDailyLog(Patient patient, String date) {
        try {
            return getDailyLog(patient, dateFromString(date));
        } catch (ParseException e) {
            return null;
        }
    }

    public DailyLog getDailyLog(Patient patient, Date date) {
        return persistLog.getDailyLog(patient, date);
    }

    public double getAverageSleep(Patient patient) {
        float[] sleepHours = getAllSleepHours(patient);
        if (sleepHours.length == 0)
            return 0;

        double sleepSum = 0.0;
        for (double h : sleepHours) {
            sleepSum += h;
        }

        return sleepSum / sleepHours.length;
    }

    public float[] getAllSleepHours(Patient patient) {
        List<DailyLog> allLogs = persistLog.getAllDailyLogs(patient);
        float[] sleepHoursArray = new float[allLogs.size()];

        for (int i = 0; i < allLogs.size(); i++) {
            DailyLog log = allLogs.get(i);
            if (log != null) {
                sleepHoursArray[i] = log.getSleepHours();
            } else {
                sleepHoursArray[i] = 0.0f;
            }
        }

        return sleepHoursArray;
    }

    public float[] getAverageSymptoms(Patient patient) {
        List<DailyLog> allLogs = persistLog.getAllDailyLogs(patient);
        int[] totalIntensities = new int[17];
        int[] numLogs = new int[17];
        float[] avgSymptoms = new float[17];

        for (DailyLog log : allLogs) {
            if (log != null) {
                List<Symptom> symptoms = log.getSymptoms();
                for (int i = 0; i < symptoms.size(); i++) {
                    Symptom symptom = symptoms.get(i);
                    totalIntensities[i] += symptom.getIntensity();
                    numLogs[i]++;
                }
            }
        }
        for (int i = 0; i < avgSymptoms.length; i++) {
            if (numLogs[i] != 0) {
                avgSymptoms[i] = (float) totalIntensities[i] / numLogs[i];
            } else {
                avgSymptoms[i] = 0.0f;
            }
        }

        return avgSymptoms;
    }

    public List<Date> getAllDates(Patient patient) {
        return persistLog.getAllDates(patient);
    }

    private static Date dateFromString(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        return formatter.parse(dateString);
    }

    public float[] getAllMoodScores(Patient patient) {
        List<DailyLog> allLogs = persistLog.getAllDailyLogs(patient);
        List<Float> moodScores = new ArrayList<>();

        for (DailyLog log : allLogs) {
            if (log != null) {
                moodScores.add((float) log.getMoodScore());
            }
        }

        Comparator<DailyLog> byDate = Comparator.comparing(DailyLog::getDate);
        allLogs.sort(byDate);

        float[] moodScoresArray = new float[moodScores.size()];
        for (int i = 0; i < moodScores.size(); i++) {
            moodScoresArray[i] = moodScores.get(i);
        }

        return moodScoresArray;
    }

    public List<String> getAllDatesAsString(Patient patient) {
        List<Date> dates = persistLog.getAllDates(patient);

        Collections.sort(dates);

        List<String> dateStrings = new ArrayList<>();
        for (Date date : dates) {
            dateStrings.add(dateToString(date));
        }

        return dateStrings;
    }

    private String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        return dateFormat.format(date);
    }
}
