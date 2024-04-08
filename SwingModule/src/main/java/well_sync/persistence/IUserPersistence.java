package well_sync.persistence;

import well_sync.objects.Doctor;
import well_sync.objects.Patient;
import well_sync.objects.UserCredentials;

import java.util.List;

public interface IUserPersistence {

    UserCredentials getUserCredentials(UserCredentials userCredentials);

    void setUserCredentials(UserCredentials user);

    Patient getPatient(UserCredentials userCredentials);

    void setPatient(Patient patient);

    Patient getPatient(String email);

    void setDoctor(Doctor doctor);

    Doctor getDoctor(UserCredentials userCredentials);

    Doctor getDoctor(String email);

    List<Patient> getPatientsList();

}
