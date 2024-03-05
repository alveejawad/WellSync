package com.well_sync.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.well_sync.objects.*;
import com.well_sync.R;
import java.util.List;

public class DoctorPageActivity extends AppCompatActivity{
    private List<Patient> patientList;
    private PatientAdapter patientAdapter;

    private EditText emailEditText;
    private Button addPatientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        patientList = new ArrayList<>();
        patientAdapter = new PatientAdapter(patientList);

        RecyclerView recyclerView = findViewById(R.id.patientList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(patientAdapter);

        emailEditText = findViewById(R.id.patient_email_input);
        addPatientButton = findViewById(R.id.add_patient);

        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                if (!email.isEmpty()) {
                    // Create a new patient and add it to the list
                    Patient newPatient = new Patient(email, "Test", "123", "A", null, 21);
                    patientList.add(newPatient);

                    // Notify the adapter of the data change
                    patientAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
