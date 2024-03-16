package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.logic.exceptions.InvalidDoctorException;
import com.well_sync.objects.Doctor;
import com.well_sync.objects.Patient;
import com.well_sync.objects.DoctorValidator;
import com.well_sync.objects.Patient;
import com.well_sync.persistence.IUserPersistence;

import javax.print.Doc;

public class DoctorHandler {
    private final IUserPersistence persistUsers;

    public DoctorHandler() {
        persistUsers = Services.getUserPersistence(true);
    }

    public DoctorHandler(IUserPersistence persistence){
        persistUsers = persistence;
    }

    //getDetails function will get the patient and give the patient to the UI layer
    public Doctor getDetails(String email) {
        return persistUsers.getDoctor(email);
    }

    public void removePatient(Doctor doctor, Patient patient) {
        persistUsers.removePatient(doctor, patient);
    }

    //editDetails function will get the userInput and set the details into the persistence layer
    public void editDetails(Doctor inputDetails) throws InvalidDoctorException {
        DoctorValidator.validateDoctor(inputDetails);

        //user info
        String email = inputDetails.getEmail();
        //got from the database
        Doctor d = persistUsers.getDoctor(email);

        //compare p wih inputDetails
        if (d == null || !d.equals(inputDetails)) {
            persistUsers.setDoctor(inputDetails);
        }
    }
    public void addPatient(Patient patient, Doctor doctor)throws InvalidDoctorException {
        persistUsers.setPatientToDoctor(patient,doctor);
    }
}
