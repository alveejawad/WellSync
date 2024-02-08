package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.logic.exceptions.InvalidCredentialsException;
import com.well_sync.objects.UserCredentials;
import com.well_sync.objects.UserCredentialsValidator;
import com.well_sync.persistence.iUserPersistence;

public class UserAuthenticationHandler {

    private final iUserPersistence persistUsers;

    public UserAuthenticationHandler() {
        persistUsers = Services.getUserPersistence();
    }

    public UserAuthenticationHandler(iUserPersistence persistence) {
        persistUsers = persistence;
    }

    public boolean login(UserCredentials inputCreds) {
        UserCredentials savedCreds = persistUsers.getUserCredentials(inputCreds);

        // no matching credentials found
        if (savedCreds == null)
            return false;

        // matching credentials found, check equality with DSO's equals() method
        // (note persistence matches only on email, we do this to check that the credentials
        // match fully, per the DSO's internal definition of equality)
        else
            return inputCreds.equals(savedCreds);
    }

    /**
     * Registers a user with the given credentials in the system, assuming the credentials
     * are valid. Note that account settings must be set using PatientHandler, and are undefined
     * for new accounts until this is done once.
     */
    public boolean register(UserCredentials credentials) {
        try {
            UserCredentialsValidator.validateCredentials(credentials);
            persistUsers.setUserCredentials(credentials);
        } catch (InvalidCredentialsException e) {
            return false;
        }

        return true;
    }
}
