package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;
import com.well_sync.persistence.IDailyLogPersistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            return persistLog.getDailyLog(patient, dateFromString(date));
        } catch (ParseException e) {
            return null;
        }
    }

    public List<Date> getAllDates(Patient patient) {
        return persistLog.getAllDates(patient);
    }

    private static Date dateFromString(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        return formatter.parse(dateString);
    }
}
