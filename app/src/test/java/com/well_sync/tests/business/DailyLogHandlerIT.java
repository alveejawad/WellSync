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
import java.util.Date;
import java.util.List;

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
        DailyLog log1 = new DailyLog("2024-03-06", 3, 10, "Slept in");

        List<Date> list1 = handler.getAllDates(patient);
        handler.setDailyLog(patient, log1);
        List<Date> list2 = handler.getAllDates(patient);

        DailyLog log2 = handler.getDailyLog(patient, "2024-03-06");

        assertEquals(log1, log2);
        assertEquals(list1.size() + 1, list2.size());
    }

    @After
    public void tearDown() {
        System.out.println("Reset database.");
        this.tempDB.delete();
        Services.clean();
    }
}
