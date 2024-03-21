package com.well_sync.logic;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

/**
 * Package-private utility class for general helper functions related to validation.
 */
abstract class ValidationUtils {

    /**
     * Valid name uses 2 or more alphabetic characters; nothing else.
     */
    public static <E extends Exception> void validateName(Class<E> exceptionType, String name) throws E {
        if (name == null || !Pattern.matches("[\\w -]{2,}", name)) {
            try {
                throw exceptionType
                        .getConstructor(String.class)
                        .newInstance("Invalid name specified");
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                     InvocationTargetException e) {
                throw new RuntimeException("Failed to instantiate invalid data exception.");
            }
        }
    }

    /**
     * Email address must contain the usual well-formed components (mailbox name,
     * host domain, and TLD suffix).
     */
    public static <E extends Exception> void validateEmail(Class<E> exceptionType, String email) throws E {

        if (email == null || !Pattern.matches("[\\w_.-]+@[\\w-]+.[a-z]{2,}", email)) {
            try {
                throw exceptionType
                        .getConstructor(String.class)
                        .newInstance("Invalid email address specified");
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                     InvocationTargetException e) {
                throw new RuntimeException("Failed to instantiate invalid data exception.");
            }
        }
    }
}
