package com.well_sync.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.well_sync.application.Services;
import com.well_sync.logic.DoctorHandler;
import com.well_sync.logic.exceptions.InvalidDoctorException;
import com.well_sync.objects.Doctor;
import com.well_sync.objects.UserCredentials;
import com.well_sync.tests.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class DoctorHandlerIT {
    private DoctorHandler handler;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting integration test for DoctorHandler");
        this.tempDB = TestUtils.copyDB();
        this.handler = new DoctorHandler();

        assertNotNull(this.tempDB);
        assertNotNull(this.handler);
    }

    @Test
    public void setAndGetDoctor() {
        Doctor d1 = new Doctor("new-doctor@example.com", "Gregory", "House");

        try {
            handler.editDetails(d1);
        } catch (InvalidDoctorException e) {
            e.printStackTrace();
        }

        Doctor d2 = handler.getDetails("new-doctor@example.com");

        assertEquals(d1, d2);
    }

    @After
    public void tearDown() {
        System.out.println("Reset database.");
        this.tempDB.delete();
        Services.clean();
    }

}
