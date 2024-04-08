package well_sync.logic;

import well_sync.application.Services;
import well_sync.logic.exceptions.InvalidDoctorException;
import well_sync.objects.Doctor;
import well_sync.objects.DoctorValidator;
import well_sync.persistence.IUserPersistence;

public class DoctorHandler {
    private final IUserPersistence persistUsers;

    public DoctorHandler() {
        persistUsers = Services.getUserPersistence(true);
    }

    public DoctorHandler(IUserPersistence persistence){
        persistUsers = persistence;
    }

    //getDetails function will get the patient and give the patient to the UI layer
    public Doctor getDetails(String email) {
        return persistUsers.getDoctor(email);
    }

    //editDetails function will get the userInput and set the details into the persistence layer
    public void editDetails(Doctor inputDetails) throws InvalidDoctorException {
        DoctorValidator.validateDoctor(inputDetails);

        //user info
        String email = inputDetails.getEmail();
        //got from the database
        Doctor d = persistUsers.getDoctor(email);

        //compare p wih inputDetails
        if (d == null || !d.equals(inputDetails)) {
            persistUsers.setDoctor(inputDetails);
        }
    }
}
