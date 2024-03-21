package com.well_sync.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.IDailyLogHandler;
import com.well_sync.logic.IPatientHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;
import com.well_sync.persistence.IDailyLogPersistence;
import com.well_sync.persistence.stubs.DailyLogPersistenceStub;
import com.well_sync.persistence.stubs.UserPersistenceStub;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DailyLogHandlerTest {

    private DailyLogHandler dailyLogHandler;
    private IPatientHandler IPatientHandler; // needed to recall patients

    @Before
    public void setup() {
        System.out.println("Starting test for DailyLogHandler");
        dailyLogHandler = new DailyLogHandler(new DailyLogPersistenceStub());
        IPatientHandler = new PatientHandler(new UserPersistenceStub());
    }

    @Test
    public void testGetDailyLog() {
        System.out.println("\nStarting testGetDailyLog");

        // retrieving known DailyLog
        Date date = new Date(123, Calendar.JANUARY, 1);
        DailyLog log = dailyLogHandler.getDailyLog(IPatientHandler.getDetails("patient1@example.com"), "2023-01-01");

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
        Patient patient = new Patient("new-patient@example.com");
        DailyLog dailyLog = new DailyLog("2024-01-17", 2, 4, "Very tired");

        dailyLogHandler.setDailyLog(patient, dailyLog);

        // set invalid log
        dailyLog = new DailyLog("2024-01-18", -42, -1, "weird day");

        try {
            dailyLogHandler.setDailyLog(patient, dailyLog);
            fail("Setting an invalid daily log did not raise an exception.");
        } catch (InvalidDailyLogException e) {
            // pass!
        }

        System.out.println("Finished testSetDailyLog.");
    }

    @Test
    public void testGetAllDates() {
        Patient p1 = IPatientHandler.getDetails("patient1@example.com");
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

    @Test
    public void testSetMedication() {
        IDailyLogPersistence mockedPersistence = mock(IDailyLogPersistence.class);
        IDailyLogHandler toTest = new DailyLogHandler(mockedPersistence);

        Date currDate = new Date();
        Patient p1 = new Patient("mock1@example.com");
        DailyLog dl1 = new DailyLog();

        try {
            dl1.addMedication("ibuprofen", 2, 200);
            toTest.setMedication(p1, dl1);
        } catch (InvalidDailyLogException e) {
            fail(e.getMessage());
        }

        when(mockedPersistence.getDailyLog(p1, currDate)).thenReturn(dl1);

        DailyLog dl2 = toTest.getDailyLog(p1, currDate);

        verify(mockedPersistence).setMedication(p1, dl1);
        verify(mockedPersistence).getDailyLog(p1, currDate);

        assertEquals(dl1, dl2);
    }

    @Test
    public void testSetSymptom() {
        IDailyLogPersistence mockedPersistence = mock(IDailyLogPersistence.class);
        IDailyLogHandler toTest = new DailyLogHandler(mockedPersistence);

        Date currDate = new Date();
        Patient p1 = new Patient("mock1@example.com");
        DailyLog dl1 = new DailyLog();

        try {
            dl1.addSymptom("headache", 3);
            toTest.setSymptoms(p1, dl1);
        } catch (InvalidDailyLogException e) {
            fail(e.getMessage());
        }

        when(mockedPersistence.getDailyLog(p1, currDate)).thenReturn(dl1);

        DailyLog dl2 = toTest.getDailyLog(p1, currDate);

        verify(mockedPersistence).setSymptoms(p1, dl1);
        verify(mockedPersistence).getDailyLog(p1, currDate);

        assertEquals(dl1, dl2);
    }

    @Test
    public void testSetSubstance() {
        IDailyLogPersistence mockedPersistence = mock(IDailyLogPersistence.class);
        IDailyLogHandler toTest = new DailyLogHandler(mockedPersistence);

        Date currDate = new Date();
        Patient p1 = new Patient("mock1@example.com");
        DailyLog dl1 = new DailyLog();

        try {
            dl1.addSubstance("caffeine", 5);
            toTest.setSubstances(p1, dl1);
        } catch (InvalidDailyLogException e) {
            fail(e.getMessage());
        }

        when(mockedPersistence.getDailyLog(p1, currDate)).thenReturn(dl1);

        DailyLog dl2 = toTest.getDailyLog(p1, currDate);

        verify(mockedPersistence).setSubstances(p1, dl1);
        verify(mockedPersistence).getDailyLog(p1, currDate);

        assertEquals(dl1, dl2);
    }

    @Test
    public void testGetAverageSleep() {
        IDailyLogPersistence mockedPersistence = mock(IDailyLogPersistence.class);
        IDailyLogHandler toTest = new DailyLogHandler(mockedPersistence);

        Patient p1 = new Patient("mock1@example.com");

        DailyLog dl1 = new DailyLog("2024-03-21", 3, 7, "");
        DailyLog dl2 = new DailyLog("2024-03-22", 3, 9, "");
        DailyLog dl3 = new DailyLog("2024-03-23", 3, 10, "");
        List<DailyLog> logList = new ArrayList<>();
        logList.add(dl1);
        logList.add(dl2);
        logList.add(dl3);

        when(mockedPersistence.getAllDailyLogs(p1)).thenReturn(logList);
        double avgSleep = toTest.getAverageSleep(p1);
        verify(mockedPersistence).getAllDailyLogs(p1);

        assertEquals(26.0/3, avgSleep, 0.0001);
    }
}
