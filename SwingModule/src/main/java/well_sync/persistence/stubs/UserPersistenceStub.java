package well_sync.persistence.stubs;

import well_sync.objects.Doctor;
import well_sync.objects.Patient;
import well_sync.objects.UserCredentials;
import well_sync.persistence.IUserPersistence;

import java.util.ArrayList;
import java.util.List;

public class UserPersistenceStub implements IUserPersistence {
    private final List<Patient> patientList;
    private final List<UserCredentials> userCredentialsList;
    private final List<Doctor> doctorList;

    public UserPersistenceStub() {
        this.patientList = new ArrayList<>();
        this.userCredentialsList = new ArrayList<>();
        this.doctorList = new ArrayList<>();

        // Sample data for USER_CREDENTIALS table
        UserCredentials userCredentials1 = new UserCredentials("patient1@example.com", "password1", "PATIENT");
        UserCredentials userCredentials2 = new UserCredentials("patient2@example.com", "password1", "PATIENT");
        UserCredentials userCredentials3 = new UserCredentials("doctor1@example.com", "password2", "DOCTOR");
        UserCredentials userCredentials4 = new UserCredentials("doctor2@example.com", "password2", "DOCTOR");
        userCredentialsList.add(userCredentials1);
        userCredentialsList.add(userCredentials2);
        userCredentialsList.add(userCredentials3);
        userCredentialsList.add(userCredentials4);

        // Sample data for PATIENTS table
        Patient patient1 = new Patient("patient1@example.com", "John", "Doe", "TYPE_A", "MALE", 30);
        Patient patient2 = new Patient("patient2@example.com", "Jane", "Smith", "TYPE_O", "FEMALE", 25);
        patientList.add(patient1);
        patientList.add(patient2);

        // Sample data for DOCTORS table
        Doctor doctor1 = new Doctor("doctor1@example.com", "Dr. Gabriel", "Young");
        Doctor doctor2 = new Doctor("doctor2@example.com", "Dr. Alvee", "Jawad");
        doctor1.addPatient(patient1);
        doctor2.addPatient(patient2);
        doctorList.add(doctor1);
        doctorList.add(doctor2);
    }

    @Override
    public UserCredentials getUserCredentials(UserCredentials userCredentials) {
        for(int i = 0; i < userCredentialsList.size(); i ++) {
            if(userCredentialsList.get(i).getEmail().equals(userCredentials.getEmail())) {
                return userCredentialsList.get(i);
            }
        }
        return null;
    }
    public Patient getPatient(String email){
        for (int  i = 0 ;i < patientList.size(); i++){
            if(patientList.get(i).getEmail().equals(email)){
                 return patientList.get(i);
            }
        }
        return null;
    }

    @Override
    public void setDoctor(Doctor doctor) {
        boolean doctorExists = false;
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorList.get(i).getEmail().equals(doctor.getEmail())) {
                doctorList.set(i, doctor);
                doctorExists = true;
                break;
            }
        }
        if (!doctorExists) {
            doctorList.add(doctor);
        }
    }

    @Override
    public Doctor getDoctor(UserCredentials userCredentials) {
        for (Doctor doctor : doctorList) {
            if (doctor.getEmail().equals(userCredentials.getEmail())) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public Doctor getDoctor(String email) {
        for (Doctor doctor : doctorList) {
            if (doctor.getEmail().equals(email)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public List<Patient> getPatientsList() {
        return this.patientList;
    }

    @Override
    public void setUserCredentials(UserCredentials user) {
        userCredentialsList.add(user);
    }

    @Override
    public Patient getPatient(UserCredentials userCredentials) {
        for (int i = 0; i < patientList.size(); i ++) {
            if (patientList.get(i).getEmail().equals(userCredentials.getEmail())) {
                return patientList.get(i);
            }
        }
        return null;
    }

    @Override
    public void setPatient(Patient patient) {
        boolean patientExists = false;

        // Check if a patient with the same email already exists
        for (int i = 0; i < patientList.size(); i++) {
            if (patientList.get(i).getEmail().equals(patient.getEmail())) {
                patientList.set(i, patient);
                patientExists = true;
                break;
            }
        }

        // If no patient with the same email is found, add the new patient
        if (!patientExists) {
            patientList.add(patient);
        }
    }

}
