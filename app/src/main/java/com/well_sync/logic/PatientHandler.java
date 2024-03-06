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
		persistUsers = Services.getUserPersistence();
	}

	public PatientHandler(IUserPersistence persistence){
		persistUsers = persistence;
	}

	//getDetails function will get the patient and give the patient to the UI layer
	public Patient getDetails(UserCredentials credentials) {
		return persistUsers.getPatient(credentials);
	}
	public Patient getDetails(String email) {
		return persistUsers.getPatient(email);
	}

	//editDetails function will get the userInput and set the details into the persistence layer
	public boolean editDetails(Patient inputDetails) {

		try {
			PatientValidator.validatePatient(inputDetails);

			//user info
			String email = inputDetails.getEmail();
			//got from the database
			Patient p = persistUsers.getPatient(email);

			//compare p wih inputDetails
			if (p == null || !p.equals(inputDetails)) {
				persistUsers.setPatient(inputDetails);
			}
		} catch (InvalidPatientException | InvalidCredentialsException e) {
			return false;
		}

		return true;
	}

}


