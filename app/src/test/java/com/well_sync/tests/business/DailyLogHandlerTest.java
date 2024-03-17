package com.well_sync.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;
import com.well_sync.persistence.stubs.DailyLogPersistenceStub;
import com.well_sync.persistence.stubs.UserPersistenceStub;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DailyLogHandlerTest {

    private DailyLogHandler dailyLogHandler;
    private PatientHandler patientHandler; // needed to recall patients

    @Before
    public void setup() {
        System.out.println("Starting test for DailyLogHandler");
        dailyLogHandler = new DailyLogHandler(new DailyLogPersistenceStub());
        patientHandler = new PatientHandler(new UserPersistenceStub());
    }

    @Test
    public void testGetDailyLog() {
        System.out.println("\nStarting testGetDailyLog");

        // retrieving known DailyLog
        Date date = new Date(123, Calendar.JANUARY, 1);
        DailyLog log = dailyLogHandler.getDailyLog(patientHandler.getDetails("patient1@example.com"), date);

        assertEquals(date, log.getDate());
        assertEquals(7, log.getMoodScore());
        assertEquals(8, log.getSleepHours());
        assertEquals("Feeling good today", log.getNotes());

        System.out.println("Finished testGetDailyLog");
    }

    @Test
    public void testSetDailyLog() throws InvalidDailyLogException {
        System.out.println("\nStarting testSetDailyLog...");

        // set valid log for patient
        Date date = new Date(124, Calendar.JANUARY, 17);
        Patient patient = new Patient("new-patient@example.com");
        DailyLog dailyLog = new DailyLog(date, 2, 4, "Very tired");

        dailyLogHandler.setDailyLog(patient, dailyLog);

        // set invalid log
        date = new Date(124, Calendar.JANUARY, 18);
        dailyLog = new DailyLog(date, -42, -1, "weird day");

        try {
            dailyLogHandler.setDailyLog(patient, dailyLog);
            fail("Setting an invalid daily log did not raise an exception.");
        } catch (InvalidDailyLogException e) {
            // pass!
        }

        System.out.println("Finished testSetDailyLog.");
    }

    @Test
    public void testDateFromString() {
        Date d1 = DailyLogHandler.DateFromString("2022-04-25");
        assertEquals(new Date(122, Calendar.APRIL, 25), d1);

        Date d2 = DailyLogHandler.DateFromString("1901-01-01");
        assertEquals(new Date(1, Calendar.JANUARY, 1), d2);

        try {
            DailyLogHandler.DateFromString("not a date");
            fail("Invalid date did not throw an exception.");
        } catch (RuntimeException e) {
            // pass!
        }
    }

    @Test
    public void testGetAllDates() {
        Patient p1 = patientHandler.getDetails("patient1@example.com");
        List<Date> datesActual = dailyLogHandler.getAllDates(p1);
        List<Date> datesExpected = new ArrayList<Date>() {{
            add(new Date(123, Calendar.JANUARY, 1));
            add(new Date(123, Calendar.JANUARY, 2));
        }};

        assertEquals(datesExpected, datesActual);

        Date newDate = new Date(123, Calendar.JANUARY, 17);
        DailyLog newLog = new DailyLog(newDate, 2, 6, "meh");
        try {
            dailyLogHandler.setDailyLog(p1, newLog);
        } catch (InvalidDailyLogException e) {
            fail(e.getMessage());
        }

        datesActual = dailyLogHandler.getAllDates(p1);
        datesExpected.add(newDate);
        assertEquals(datesExpected, datesActual);
    }
}
