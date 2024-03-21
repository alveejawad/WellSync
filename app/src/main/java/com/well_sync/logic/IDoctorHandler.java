package com.well_sync.logic;

import com.well_sync.logic.exceptions.InvalidDoctorException;
import com.well_sync.objects.Doctor;
import com.well_sync.objects.Patient;

/**
 * Saves and retrieves Doctors' user details and manages the lists of patients whose
 * medical records they oversee.
 */
public interface IDoctorHandler {
    //getDetails function will get the patient and give the patient to the UI layer
    Doctor getDetails(String email);

    void removePatient(Doctor doctor, Patient patient);

    //editDetails function will get the userInput and set the details into the persistence layer
    void editDetails(Doctor inputDetails) throws InvalidDoctorException;

    void addPatient(Patient patient, Doctor doctor) throws InvalidDoctorException;
}
