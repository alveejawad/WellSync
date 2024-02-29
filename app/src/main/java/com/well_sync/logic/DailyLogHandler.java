package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.DailyLogValidator;
import com.well_sync.objects.Patient;
import com.well_sync.persistence.IDailyLogPersistence;

import java.util.Date;

public class DailyLogHandler {

    private final IDailyLogPersistence persistLog;

    public DailyLogHandler() {
        persistLog = Services.getDailyLogPersistence();
    }

    public DailyLogHandler(IDailyLogPersistence persistence){
        persistLog = persistence;
    }

    public boolean setDailyLog(Patient patient, DailyLog dailyLog) {
        try {
            DailyLogValidator.validateLog(dailyLog);
            persistLog.setDailyLog(patient, dailyLog);
        } catch (InvalidDailyLogException e) {
            return false;
        }

        return true;
    }

    public DailyLog getDailyLog(Patient patient, Date date) {
        return persistLog.getDailyLog(patient, date);
    }
}
