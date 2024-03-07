package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.well_sync.objects.*;
import com.well_sync.logic.*;
import com.well_sync.persistence.IUserPersistence;
import com.well_sync.R;
import com.well_sync.persistence.stubs.UserPersistenceStub;

import java.util.List;



public class DoctorPageActivity extends AppCompatActivity{

    private Doctor doctor;
    private Intent intent;
    private String email;
    private IUserPersistence userPersistence;
    private List<Patient> patientList;
    private PatientHandler patientHandler;
    private DoctorHandler doctorHandler;
    private PatientAdapter patientAdapter;

    private EditText emailEditText;
    private Button addPatientButton;

    private String name;
    private int age;
    private String bloodType;
    private String gender;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        intent = getIntent();
        email = intent.getStringExtra("email");
        doctor = new Doctor(email);

        patientHandler = new PatientHandler();
        userPersistence = new UserPersistenceStub();
        patientList = userPersistence.getPatientsList();
        patientAdapter = new PatientAdapter(patientList);

        RecyclerView recyclerView = findViewById(R.id.patientList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(patientAdapter);

        // Set a click listener for RecyclerView items
        patientAdapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Patient selectedPatient = patientList.get(position);
                name = selectedPatient.getName();
                age = selectedPatient.getAge();
                bloodType = selectedPatient.getBloodType().toString();
                gender = selectedPatient.getSex().toString();
                // Start the PatientInfoActivity and pass the selected patient's information
                Intent intent = new Intent(DoctorPageActivity.this, PatientInfoActivity.class);
                intent.putExtra("patient", selectedPatient);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("sex", gender);
                intent.putExtra("bloodtype", bloodType);
                DoctorPageActivity.this.startActivity(intent);
            }
        });

        emailEditText = findViewById(R.id.patient_email_input);
        addPatientButton = findViewById(R.id.add_patient);

        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                // check if the input for email is empty
                if(email.isEmpty()) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(DoctorPageActivity.this, "Email cannot be empty.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }

                // check if patient is not existing
                Patient existedPatient = userPersistence.getPatient(email);
                if(existedPatient == null) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(DoctorPageActivity.this, "Sorry, the patient does not exist.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                for(int i = 0; i < patientList.size(); i++) { // go over the list of patients
                    if(existedPatient == patientList.get(i)) { // check if we add a duplicate patient
                        // Show a Toast or handle the validation error as needed
                        Toast.makeText(DoctorPageActivity.this, "Duplicated patient, please try again", Toast.LENGTH_SHORT).show();
                        return; // Exit the onClick method to prevent further execution
                    }
                }

                patientList.add(existedPatient);
                doctor.addPatient(existedPatient);

                // Notify the adapter of the data change
                patientAdapter.notifyDataSetChanged();
            }
        });
    }
}



