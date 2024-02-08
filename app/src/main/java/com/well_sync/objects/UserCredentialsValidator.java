package com.well_sync.objects;

import com.well_sync.logic.exceptions.InvalidCredentialsException;

import java.util.regex.Pattern;

public class UserCredentialsValidator {

    /**
     * Validate credentials object and all its components.
     */
    public static void validateCredentials(UserCredentials credentials) throws InvalidCredentialsException {
        if (credentials == null)
            throw new InvalidCredentialsException("Credentials object undefined.");

        validateEmail(credentials.getEmail());
        validatePassword(credentials.getPassword());
    }

    /**
     * Email address must contain the usual well-formed components (mailbox name,
     * host domain, and TLD suffix).
     */
    public static void validateEmail(String email) throws InvalidCredentialsException {
        if (email == null)
            throw new InvalidCredentialsException("No email address specified.");
        if (!Pattern.matches("[\\w_.-]+@[\\w-]+.[a-z]{2}", email))
            throw new InvalidCredentialsException("Invalid/malformed email address.");
    }

    /**
     * Password must be 8-64 characters long to be valid, and contain no spaces.
     */
    public static void validatePassword(String password) throws InvalidCredentialsException {
        if (password == null)
            throw new InvalidCredentialsException("No password specified.");
        if (!Pattern.matches("((?=.)[^ ]){8,64}", password))
            throw new InvalidCredentialsException("Invalid password.");
    }

}
