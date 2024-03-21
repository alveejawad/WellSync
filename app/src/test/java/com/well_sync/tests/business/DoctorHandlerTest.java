package com.well_sync.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.well_sync.logic.DoctorHandler;
import com.well_sync.logic.IDoctorHandler;
import com.well_sync.logic.exceptions.InvalidDoctorException;
import com.well_sync.objects.Doctor;
import com.well_sync.persistence.stubs.UserPersistenceStub;

import org.junit.Before;
import org.junit.Test;

public class DoctorHandlerTest {

    private IDoctorHandler doctorHandler;

    @Before
    public void setup() {
        System.out.println("Starting test for DoctorHandler");
        doctorHandler = new DoctorHandler(new UserPersistenceStub());
    }

    @Test
    public void testGetDetails() {
        System.out.println("\nStarting testGetDetails");

        String email = "doctor1@example.com";
        Doctor doctor = doctorHandler.getDetails(email);

        assertEquals("doctor1@example.com", doctor.getEmail());
        assertEquals("Dr. Gabriel", doctor.getFirstName());
        assertEquals("Young", doctor.getLastName());

        System.out.println("Finished testGetDetails");
    }

    @Test
    public void testEditDetails() throws InvalidDoctorException {
        System.out.println("\nStarting testEditDetails");

        // this should succeed
        Doctor doctor1 = new Doctor("new-doctor@example.com", "Gregory", "House");
        doctorHandler.editDetails(doctor1);

        try {
            // this should fail
            Doctor doctor2 = new Doctor("invalid email address!");
            doctorHandler.editDetails(doctor2);
            fail("Creating doctor with malformed email address did not throw an exception.");
        } catch (InvalidDoctorException e) {
            // pass!
        }

        System.out.println("Finished testEditDetails");
    }
}