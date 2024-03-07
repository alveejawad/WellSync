package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.logic.exceptions.InvalidCredentialsException;
import com.well_sync.objects.Doctor;
import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;
import com.well_sync.objects.UserCredentialsValidator;
import com.well_sync.persistence.IUserPersistence;

public class UserAuthenticationHandler {

    private final IUserPersistence persistUsers;

    public UserAuthenticationHandler() {
        persistUsers = Services.getUserPersistence(true);
    }

    public UserAuthenticationHandler(IUserPersistence persistence) {
        persistUsers = persistence;
    }

    public void login(UserCredentials inputCreds) throws InvalidCredentialsException {
        UserCredentials savedCreds = persistUsers.getUserCredentials(inputCreds);

        // no matching credentials found
        if (savedCreds == null)
            throw new InvalidCredentialsException("Invalid email or password.");

        // matching credentials found, check equality with DSO's equals() method
        // (note persistence matches only on email, we do this to check that the credentials
        // match fully, per the DSO's internal definition of equality)
        else if (!inputCreds.equals(savedCreds))
            throw new InvalidCredentialsException("Invalid email or password.");
    }

    /**
     * Registers a user with the given credentials in the system, assuming the credentials
     * are valid. Note that account settings must be set using PatientHandler, and are undefined
     * for new accounts until this is done once.
     */
    public void register(UserCredentials credentials) throws InvalidCredentialsException {
        UserCredentialsValidator.validateCredentials(credentials);
        persistUsers.setUserCredentials(credentials);
        if (credentials.getRole() != null){
            if (credentials.getRole() == UserCredentials.Role.PATIENT){
                Patient patient = new Patient(credentials.getEmail());
                persistUsers.setPatient(patient);
            }
            else{
                Doctor doctor = new Doctor(credentials.getEmail());
                persistUsers.setDoctor(doctor);
            }
        }
    }
}
