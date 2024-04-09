package org.softeng.assignment.logic;

import org.softeng.assignment.logic.exceptions.InvalidDoctorException;
import org.softeng.assignment.objects.Doctor;


/**
 * Package-private utility class to validate fields in the Doctor DSO.
 */
abstract class DoctorValidator {
    public static void validateDoctor(Doctor doctor) throws InvalidDoctorException {
        if (doctor == null)
            throw new InvalidDoctorException("Doctor details object undefined.");

        ValidationUtils.validateEmail(InvalidDoctorException.class, doctor.getEmail());
        ValidationUtils.validateName(InvalidDoctorException.class, doctor.getFirstName());
        ValidationUtils.validateName(InvalidDoctorException.class, doctor.getLastName());
    }
}
