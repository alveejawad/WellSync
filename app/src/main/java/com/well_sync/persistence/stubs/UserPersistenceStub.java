package com.well_sync.persistence.stubs;

import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.UserPersistence;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class UserPersistenceStub implements UserPersistence {
    private final List<Patient> patientList;
    private final List<UserCredentials> userCredentialsList;

    public UserPersistenceStub() {
        this.patientList = new ArrayList<>();
        this.userCredentialsList = new ArrayList<>();

        patientList.add(new Patient("test123@umanitoba.ca", "Test 123", Patient.BloodType.TYPE_A, Patient.Sex.UNSPECIFIED, 21));
        patientList.add(new Patient("muhammad@umanitoba.ca", "Muhammad", Patient.BloodType.TYPE_A, Patient.Sex.MALE, 23));
        patientList.add(new Patient("test456@umanitoba.ca", "Test 456", Patient.BloodType.TYPE_A, Patient.Sex.UNSPECIFIED, 20));

        userCredentialsList.add(new UserCredentials("test123@umanitoba.ca", "test123"));
        userCredentialsList.add(new UserCredentials("muhammad@umanitoba.ca", "gossipzilla"));
        userCredentialsList.add(new UserCredentials("test456@umanitoba.ca", "test456"));
    }

    @Override
    public UserCredentials getUserCredentials(UserCredentials userCredentials) {
        for(int i = 0; i < userCredentialsList.size(); i ++) {
            if(userCredentialsList.get(i).getEmail() == userCredentials.getEmail()) {
                return userCredentialsList.get(i);
            }
        }
        return null;
    }

    @Override
    public void setUserCredentials(UserCredentials user) {
        userCredentialsList.add(user);
    }

    @Override
    public Patient getPatient(UserCredentials userCredentials) {
        for(int i = 0; i < patientList.size(); i ++) {
            if(patientList.get(i).getEmail() == userCredentials.getEmail()) {
                return patientList.get(i);
            }
        }
        return null;
    }

    @Override
    public void setPatient(Patient patient) {
        patientList.add(patient);
    }
}
