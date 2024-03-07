package com.well_sync.tests.business;

import com.well_sync.application.Services;
import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.exceptions.InvalidPatientException;
import com.well_sync.objects.Patient;
import com.well_sync.tests.utils.TestUtils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class PatientHandlerIT {
    private PatientHandler handler;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting integration test for PatientHandler");
        this.tempDB = TestUtils.copyDB();
        this.handler = new PatientHandler();

        assertNotNull(this.tempDB);
        assertNotNull(this.handler);
    }

    @Test
    public void setAndGetPatient() {
        Patient p1 = new Patient(
                "integration@test.com",
                "Integration",
                "Test",
                "A+",
                "decline to specify",
                22
        );

        try {
            handler.editDetails(p1);
        } catch (InvalidPatientException e) {
            e.printStackTrace();
        }

        Patient p2 = handler.getDetails("integration@test.com");

        assertEquals(p1, p2);
    }

    @After
    public void tearDown() {
        System.out.println("Reset database.");
        this.tempDB.delete();
        Services.clean();
    }

}
