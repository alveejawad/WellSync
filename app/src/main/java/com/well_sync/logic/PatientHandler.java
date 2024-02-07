package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;
import com.well_sync.persistence.UserPersistence;

public class PatientHandler {

	private final UserPersistence persistUsers;

	public PatientHandler() {
		persistUsers = Services.getUserPersistence();
	}

	public PatientHandler(UserPersistence persistence){
		persistUsers = persistence;
	}

	//getDetails function will get the patient and give the patient to the UI layer
	public Patient getDetails(UserCredentials credentials) {
		return persistUsers.getPatient(credentials);
	}

	//editDetails function will get the userInput and set the details into the persistence layer
	public void editDetails(Patient inputDetails) {
		persistUsers.setPatient(inputDetails);
	}
}


