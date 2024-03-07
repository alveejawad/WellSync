package com.well_sync.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import com.well_sync.application.Services;
import com.well_sync.logic.DoctorHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.UserAuthenticationHandler;
import com.well_sync.logic.exceptions.InvalidCredentialsException;
import com.well_sync.objects.UserCredentials;
import com.well_sync.tests.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class UserAuthenticationHandlerIT {
    private UserAuthenticationHandler authHandler;
    private PatientHandler patientHandler;
    private DoctorHandler doctorHandler;
    private File tempDB;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting integration test for UserAuthenticationHandler");
        this.tempDB = TestUtils.copyDB();
        this.authHandler = new UserAuthenticationHandler();
        this.patientHandler = new PatientHandler();
        this.doctorHandler = new DoctorHandler();

        assertNotNull(this.tempDB);
        assertNotNull(this.authHandler);
    }

    @Test
    public void registerThenLogin() throws InvalidCredentialsException {
        UserCredentials creds = new UserCredentials("new-email@example.net", "top-secret-password");
        authHandler.register(creds);

        // this should work
        authHandler.login(creds);

        UserCredentials creds2 = new UserCredentials("new-email@example.net", "wrong-password");
        try {
            // this should not work
            authHandler.login(creds2);
            fail("Login succeeded with invalid password.");
        } catch (InvalidCredentialsException e) {
            // pass!
        }
    }

    @After
    public void tearDown() {
        System.out.println("Reset database.");
        this.tempDB.delete();
        Services.clean();
    }

}
