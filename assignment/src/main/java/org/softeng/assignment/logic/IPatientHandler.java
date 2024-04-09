package org.softeng.assignment.logic;

import org.softeng.assignment.logic.exceptions.InvalidPatientException;
import org.softeng.assignment.objects.Patient;

/**
 * Saves and retrieves personal information on Patient users.
 */
public interface IPatientHandler {
    Patient getDetails(String email);

    void editPatientDetails(Patient patient) throws InvalidPatientException;

    void addPatient(Patient patient) throws InvalidPatientException;

}
