package com.well_sync.persistence.stubs;

import com.well_sync.objects.Doctor;
import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.IUserPersistence;

import java.util.ArrayList;
import java.util.List;

public class UserPersistenceStub implements IUserPersistence {
    private final List<Patient> patientList;
    private final List<UserCredentials> userCredentialsList;

    public UserPersistenceStub() {
        this.patientList = new ArrayList<>();
        this.userCredentialsList = new ArrayList<>();

        // Sample data for USER_CREDENTIALS table
        userCredentialsList.add(new UserCredentials("test1@example.com", "password1"));
        userCredentialsList.add(new UserCredentials("test2@example.com", "password2"));

        // Sample data for PATIENTS table
        patientList.add(new Patient("patient1@example.com", "John", "Doe", "TYPE_A", "MALE", 30));
        patientList.add(new Patient("patient2@example.com", "Jane", "Smith", "TYPE_O", "FEMALE", 25));
    }

    @Override
    public UserCredentials getUserCredentials(UserCredentials userCredentials) {
        for(int i = 0; i < userCredentialsList.size(); i ++) {
            if(userCredentialsList.get(i).getEmail().equals(userCredentials.getEmail())) {
                return userCredentialsList.get(i);
            }
        }
        return null;
    }
    public Patient getPatient(String email){
        for (int  i = 0 ;i < patientList.size(); i++){
            if(patientList.get(i).getEmail().equals(email)){
                 return patientList.get(i);
            }
        }
        return null;
    }

    @Override
    public void setDoctor(Doctor doctor) {

    }

    @Override
    public Doctor getDoctor(UserCredentials userCredentials) {
        return null;
    }

    @Override
    public Doctor getDoctor(String email) {
        return null;
    }

    @Override
    public List<Patient> getPatientsList() {
        return null;
    }

    @Override
    public void setUserCredentials(UserCredentials user) {
        userCredentialsList.add(user);
    }

    @Override
    public Patient getPatient(UserCredentials userCredentials) {
        for (int i = 0; i < patientList.size(); i ++) {
            if (patientList.get(i).getEmail().equals(userCredentials.getEmail())) {
                return patientList.get(i);
            }
        }
        return null;
    }

    public Patient getPatient(Patient patient) {
        for (int i = 0; i < patientList.size(); i ++) {
            if (patientList.get(i).getEmail().equals(patient.getEmail())) {
                return patientList.get(i);
            }
        }
        return null;
    }

    @Override
    public void setPatient(Patient patient) {
        boolean patientExists = false;

        // Check if a patient with the same email already exists
        for (int i = 0; i < patientList.size(); i++) {
            if (patientList.get(i).getEmail().equals(patient.getEmail())) {
                patientList.set(i, patient);
                patientExists = true;
                break;
            }
        }

        // If no patient with the same email is found, add the new patient
        if (!patientExists) {
            patientList.add(patient);
        }
    }


}
