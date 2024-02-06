package com.well_sync.logic;

import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;

public class UserValidationHandler {

    private UserPersistence persistUsers;

    public UserValidationHandler() {
        // TODO
    }

    public boolean login(UserCredentials inputCreds) {
        UserCredentials savedCreds = persistUsers.getUserCredentials(inputCreds);
        return inputCreds.equals(savedCreds);
    }

    public void register(UserCredentials credentials) {
        persistUsers.setUserCredentials(credentials);
    }
}
