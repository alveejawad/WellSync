package com.well_sync.persistence;

import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;

public interface UserPersistence {

    UserCredentials getUserCredentials(UserCredentials userCredentials);

    void setUserCredentials(UserCredentials user);

    Patient getPatient(UserCredentials userCredentials);

    void setPatient(Patient patient);
}
