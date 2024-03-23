package com.well_sync.logic;

import com.well_sync.logic.exceptions.InvalidPatientException;
import com.well_sync.objects.Patient;

import java.util.List;

/**
 * Saves and retrieves personal information on Patient users.
 */
public interface IPatientHandler {
    Patient getDetails(String email);

    void editPatientDetails(Patient patient) throws InvalidPatientException;

    void addPatient(Patient patient) throws InvalidPatientException;

    /**
     * Returns all patients registered in the system.
     */
    List<Patient> getAllPatientsList();
}
