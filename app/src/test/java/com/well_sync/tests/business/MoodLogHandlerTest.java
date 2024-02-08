package com.well_sync.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.well_sync.logic.MoodLogHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.objects.MoodLog;
import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.stubs.MoodLogPersistenceStub;
import com.well_sync.persistence.stubs.UserPersistenceStub;

import java.util.Calendar;
import java.util.Date;

public class MoodLogHandlerTest {

    private MoodLogHandler moodLogHandler;
    private PatientHandler patientHandler; // needed to recall patients

    @Before
    public void setup() {
        System.out.println("Starting test for MoodLogHandler");
        moodLogHandler = new MoodLogHandler(new MoodLogPersistenceStub());
        patientHandler = new PatientHandler(new UserPersistenceStub());
    }

    @Test
    public void testGetMoodLog() {
        System.out.println("\nStarting testGetMoodLog");

        // replicating known MoodLog in database stub, and retrieving it
        Date date = new Date(124, Calendar.FEBRUARY, 7);
        MoodLog knownLog = new MoodLog(date, 3, 6, "Anxious");
        MoodLog retrievedLog = moodLogHandler.getMoodLog(
                patientHandler.getDetails(new UserCredentials("test123@umanitoba.ca", "test123")),
                date
        );
        assertEquals(knownLog, retrievedLog);

        System.out.println("Finished testGetMoodLog");
    }

    @Test
    public void testSetMoodLog() {
        System.out.println("\nStarting testSetMoodLog...");

        // set valid log for patient
        Date date = new Date(124, Calendar.JANUARY, 17);
        Patient patient = new Patient("new-patient@example.com");
        MoodLog moodLog = new MoodLog(date, 2, 4, "Very tired");

        assertTrue(moodLogHandler.setMoodLog(patient, moodLog));

        // check that log was inserted
        assertEquals(moodLog, moodLogHandler.getMoodLog(patient, date));


        // set invalid log
        date = new Date(124, Calendar.JANUARY, 18);
        moodLog = new MoodLog(date, -42, -1, "weird day");

        assertFalse(moodLogHandler.setMoodLog(patient, moodLog));

        // check that log was not inserted
        assertNull(moodLogHandler.getMoodLog(patient, date));


        System.out.println("Finished testSetMoodLog.");
    }
}
