package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.UserPersistence;

public class UserValidationHandler {

    private final UserPersistence persistUsers;

    public UserValidationHandler() {
        persistUsers = Services.getUserPersistence();
    }

    public UserValidationHandler(UserPersistence persistence) {
        persistUsers = persistence;
    }

    public boolean login(UserCredentials inputCreds) {
        UserCredentials savedCreds = persistUsers.getUserCredentials(inputCreds);
        return inputCreds.equals(savedCreds);
    }

    public void register(UserCredentials credentials) {
        // User details (Patient DSO) must be set separately
        persistUsers.setUserCredentials(credentials);
    }
}
