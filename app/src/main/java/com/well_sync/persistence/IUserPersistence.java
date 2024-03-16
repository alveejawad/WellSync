package com.well_sync.persistence;

import com.well_sync.objects.Doctor;
import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;

import java.util.List;

public interface IUserPersistence {

    UserCredentials getUserCredentials(UserCredentials userCredentials);

    void setUserCredentials(UserCredentials user);

    Patient getPatient(UserCredentials userCredentials);

    void setPatient(Patient patient);

    Patient getPatient(String email);

    void setDoctor(Doctor doctor);

    Doctor getDoctor(UserCredentials userCredentials);

    Doctor getDoctor(String email);

    voic removePatient(Doctor doctor, Patient patient);

    List<Patient> getPatientsList();

}
