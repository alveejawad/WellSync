package com.well_sync.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.well_sync.application.Services;
import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;
import com.well_sync.tests.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class DailyLogHandlerIT {
    private DailyLogHandler handler;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting integration test for DailyLogHandler");
        this.tempDB = TestUtils.copyDB();
        this.handler = new DailyLogHandler();

        assertNotNull(this.tempDB);
        assertNotNull(this.handler);
    }

    @Test
    public void setAndGetLog() throws InvalidDailyLogException {
        Patient patient = new Patient("patient1@example.com");
        Date date = new Date(124, Calendar.MARCH, 6);
        DailyLog log1 = new DailyLog(date, 3, 10, "Slept in");

        handler.setDailyLog(patient, log1);

        DailyLog log2 = handler.getDailyLog(patient, date);

        assertEquals(log1.getDate(), log2.getDate());
        assertEquals(log1.getNotes(), log2.getNotes());
        assertEquals(log1.getSleepHours(), log2.getSleepHours());
        assertEquals(log1.getMoodScore(), log2.getMoodScore());
    }

    @After
    public void tearDown() {
        System.out.println("Reset database.");
        this.tempDB.delete();
        Services.clean();
    }
}
