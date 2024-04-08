package well_sync.logic;

import well_sync.application.Services;
import well_sync.logic.exceptions.InvalidPatientException;
import well_sync.objects.Patient;
import well_sync.objects.PatientValidator;
import well_sync.persistence.IUserPersistence;

public class PatientHandler {

	private final IUserPersistence persistUsers;

	public PatientHandler() {
		persistUsers = Services.getUserPersistence(true);
	}

	public PatientHandler(IUserPersistence persistence){
		persistUsers = persistence;
	}

	public Patient getDetails(String email) {
		return persistUsers.getPatient(email);
	}

	//editDetails function will get the userInput and set the details into the persistence layer
	public void editDetails(Patient inputDetails) throws InvalidPatientException {
		PatientValidator.validatePatient(inputDetails);

		//user info
		String email = inputDetails.getEmail();
		//got from the database
		Patient p = persistUsers.getPatient(email);

		//compare p wih inputDetails
		if (p == null || !p.equals(inputDetails)) {
			persistUsers.setPatient(inputDetails);
		}
	}

}


