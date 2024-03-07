package com.well_sync.tests.business;

import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.exceptions.InvalidPatientException;
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

        String email = "patient1@example.com";
        Patient patient = patientHandler.getDetails(email);

        assertEquals(patient.getEmail(), email);
        assertEquals(patient.getFirstName(), "John");
        assertEquals(patient.getLastName(), "Doe");
        assertEquals(patient.getBloodType(), Patient.BloodType.TYPE_A);
        assertEquals(patient.getSex(), Patient.Sex.MALE);
        assertEquals(patient.getAge(), 30);

        System.out.println("Finished testGetDetails");
    }

    @Test
    public void testEditDetails() throws InvalidPatientException {
        System.out.println("\nStarting testEditDetails");

        Patient patient = new Patient("new-patient@example.com", "Jane", "Newman", "O", "F", 0);
        patientHandler.editDetails(patient);

        try {
            patient = new Patient("invalid email address!");
            patientHandler.editDetails(patient);
            fail("Creating patient with malformed email address did not throw an exception.");
        } catch (InvalidPatientException e) {
            // pass!
        }

        System.out.println("Finished testEditDetails");
    }
}