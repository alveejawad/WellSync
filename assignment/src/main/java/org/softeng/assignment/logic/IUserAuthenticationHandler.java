package org.softeng.assignment.logic;

import org.softeng.assignment.logic.exceptions.InvalidCredentialsException;
import org.softeng.assignment.objects.UserCredentials;

/**
 * Manages the login and registration of users.
 */
public interface IUserAuthenticationHandler {
    UserCredentials.Role login(UserCredentials inputCreds) throws InvalidCredentialsException;

    void register(UserCredentials credentials) throws InvalidCredentialsException;
}
