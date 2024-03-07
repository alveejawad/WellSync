package com.well_sync.tests.business;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.well_sync.logic.UserAuthenticationHandler;
import com.well_sync.logic.exceptions.InvalidCredentialsException;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.stubs.UserPersistenceStub;

public class UserAuthenticationHandlerTest {

    private UserAuthenticationHandler userAuthHandler;

    @Before
    public void setup() {
        System.out.println("Starting test for UserAuthenticationHandler");
        userAuthHandler = new UserAuthenticationHandler(new UserPersistenceStub());
    }

    @Test
    public void testLogin() throws InvalidCredentialsException {
        System.out.println("\nStarting testLogin...");
        UserCredentials creds;

        // these credentials are known to be in the database stub
        creds = new UserCredentials("test1@example.com", "password1");
        userAuthHandler.login(creds);

        // these credentials are known to be in the database stub
        creds = new UserCredentials("test2@example.com", "password2");
        userAuthHandler.login(creds);

        // this email is valid, but not the password
        try {
            creds = new UserCredentials("test1@example.com", "idontknow");
            userAuthHandler.login(creds);
            fail("Invalid password did not throw an exception.");
        } catch (InvalidCredentialsException e) {
            // pass!
        }

        // email does not exist
        try {
            creds = new UserCredentials("unknown@imaginary.com", "foobar");
            userAuthHandler.login(creds);
            fail("Invalid email & password did not throw an exception.");
        } catch (InvalidCredentialsException e) {
            // pass!
        }

        System.out.println("Finished testLogin.");
    }

    @Test
    public void testRegister() throws InvalidCredentialsException {
        System.out.println("\nStarting testRegister");
        UserCredentials creds;

        creds = new UserCredentials("new-user@umanitoba.ca", "nicepassword123", UserCredentials.Role.PATIENT);
        userAuthHandler.register(creds);

        // these credentials are well-formed
        creds = new UserCredentials("new-user@umanitoba.ca", "nicepassword123", UserCredentials.Role.DOCTOR);
        userAuthHandler.register(creds);

        // malformed email address
        try {
            creds = new UserCredentials("cash$$$money@scam.co.uk", "nicepassword123", UserCredentials.Role.DOCTOR);
            userAuthHandler.register(creds);
            fail("Malformed email did not throw an exception.");
        } catch (InvalidCredentialsException e) {
            // pass!
        }

        // malformed password
        try {
            creds = new UserCredentials("new-user-2@umanitoba.ca", "no spaces allowed", UserCredentials.Role.DOCTOR);
            userAuthHandler.register(creds);
            fail("Malformed password did not throw an exception.");
        } catch (InvalidCredentialsException e) {
            // pass!
        }

        System.out.println("Finished testRegister");
    }

}