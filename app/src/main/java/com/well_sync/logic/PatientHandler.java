package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.logic.exceptions.InvalidCredentialsException;
import com.well_sync.logic.exceptions.InvalidPatientException;
import com.well_sync.objects.Patient;
import com.well_sync.objects.PatientValidator;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.IUserPersistence;

public class PatientHandler {

	private final IUserPersistence persistUsers;

	public PatientHandler() {
		persistUsers = Services.getUserPersistence(true);
	}

	public PatientHandler(IUserPersistence persistence){
		persistUsers = persistence;
	}

	//getDetails function will get the patient and give the patient to the UI layer
	public Patient getDetails(UserCredentials credentials) {
		return persistUsers.getPatient(credentials);
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


