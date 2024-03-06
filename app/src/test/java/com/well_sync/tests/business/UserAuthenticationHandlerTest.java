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
    public void testLogin() {
        System.out.println("\nStarting testLogin...");
        UserCredentials creds;

        // these credentials are known to be in the database stub
        creds = new UserCredentials("test123@umanitoba.ca", "test123");
        userAuthHandler.login(creds);

        // these credentials are known to be in the database stub
        creds = new UserCredentials("test123@umanitoba.ca", "test123");
        userAuthHandler.login(creds);

        // this email is valid, but not the password
        creds = new UserCredentials("test123@umanitoba.ca", "idontknow");
        userAuthHandler.login(creds);

        // email does not exist
        creds = new UserCredentials("unknown@imaginary.com", "foobar");
        userAuthHandler.login(creds);

        System.out.println("Finished testLogin.");
    }

    @Test
    public void testRegister() {
        System.out.println("\nStarting testRegister");
        UserCredentials creds;

        creds = new UserCredentials("new-user@umanitoba.ca", "nicepassword123", UserCredentials.Role.PATIENT);
        userAuthHandler.register(creds);

        // these credentials are well-formed
        creds = new UserCredentials("new-user@umanitoba.ca", "nicepassword123", UserCredentials.Role.DOCTOR);
        userAuthHandler.register(creds);

        // malformed email address
        creds = new UserCredentials("cash$$$money@scam.co.uk", "nicepassword123", UserCredentials.Role.DOCTOR);
        userAuthHandler.register(creds);

        // malformed password
        creds = new UserCredentials("new-user-2@umanitoba.ca", "no spaces allowed", UserCredentials.Role.DOCTOR);
        userAuthHandler.register(creds);

        System.out.println("Finished testRegister");
    }

}