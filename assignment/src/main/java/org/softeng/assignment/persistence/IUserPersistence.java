package org.softeng.assignment.persistence;

import org.softeng.assignment.objects.Doctor;
import org.softeng.assignment.objects.Patient;
import org.softeng.assignment.objects.UserCredentials;

import java.util.List;

public interface IUserPersistence {

    UserCredentials getUserCredentials(UserCredentials userCredentials);

    void setUserCredentials(UserCredentials user);

    void createPatient(Patient patient);

    Patient getPatient(String email);

    void editPatientDetails(Patient patient);

    void createDoctor(Doctor doctor);

    Doctor getDoctor(String email);

    void removePatientFromDoctor(Doctor doctor, Patient patient);

    void assignPatientToDoctor(Patient patient, Doctor doctor);

    List<Patient> getAllPatientsForDoctor(Doctor doctor);

}
