package com.well_sync.tests.business;

import com.well_sync.logic.PatientHandler;
import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.stubs.UserPersistenceStub;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PatientHandlerTest {

    private PatientHandler patientHandler;

    @Before
    public void setup() {
        System.out.println("Starting test for PatientHandlerTest");
        patientHandler = new PatientHandler(new UserPersistenceStub());
    }

    @Test
    public void testGetDetails() {
        System.out.println("\nStarting testGetDetails");

        UserCredentials creds = new UserCredentials("test456@umanitoba.ca", "test456");
        Patient patient = patientHandler.getDetails(creds);

        assertEquals(patient.getEmail(), creds.getEmail());

        System.out.println("Finished testGetDetails");
    }

    @Test
    public void testEditDetails() {
        System.out.println("\nStarting testEditDetails");

        Patient patient = new Patient("new-patient@example.com", "Jane Newman", Patient.BloodType.TYPE_O, Patient.Sex.FEMALE, 0);
        assertTrue(patientHandler.editDetails(patient));

        patient = new Patient("invalid email address!");
        assertFalse(patientHandler.editDetails(patient));

        System.out.println("Finished testEditDetails");
    }
}
