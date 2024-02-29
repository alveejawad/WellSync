package com.well_sync.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.stubs.DailyLogPersistenceStub;
import com.well_sync.persistence.stubs.UserPersistenceStub;

import java.util.Calendar;
import java.util.Date;

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

        // replicating known DailyLog in database stub, and retrieving it
        Date date = new Date(124, Calendar.FEBRUARY, 7);
        DailyLog knownLog = new DailyLog(date, 3, 6, "Anxious");
        DailyLog retrievedLog = dailyLogHandler.getDailyLog(
                patientHandler.getDetails(new UserCredentials("test123@umanitoba.ca", "test123")),
                date
        );
        assertEquals(knownLog, retrievedLog);

        System.out.println("Finished testGetDailyLog");
    }

    @Test
    public void testSetDailyLog() {
        System.out.println("\nStarting testSetDailyLog...");

        // set valid log for patient
        Date date = new Date(124, Calendar.JANUARY, 17);
        Patient patient = new Patient("new-patient@example.com");
        DailyLog dailyLog = new DailyLog(date, 2, 4, "Very tired");

        assertTrue(dailyLogHandler.setDailyLog(patient, dailyLog));

        // check that log was inserted
        assertEquals(dailyLog, dailyLogHandler.getDailyLog(patient, date));


        // set invalid log
        date = new Date(124, Calendar.JANUARY, 18);
        dailyLog = new DailyLog(date, -42, -1, "weird day");

        assertFalse(dailyLogHandler.setDailyLog(patient, dailyLog));

        // check that log was not inserted
        assertNull(dailyLogHandler.getDailyLog(patient, date));


        System.out.println("Finished testSetDailyLog.");
    }
}
