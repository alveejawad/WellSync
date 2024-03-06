package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.well_sync.R;
import com.well_sync.objects.Patient;

public class PatientInfoActivity extends AppCompatActivity {
    private TextView nameTextView, ageTextView, genderTextView, bloodTypeTextView;
    private EditText adviseEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        // Initialize views
        nameTextView = findViewById(R.id.name_patient);
        ageTextView = findViewById(R.id.age_patient);
        genderTextView = findViewById(R.id.gender_patient);
        bloodTypeTextView = findViewById(R.id.bloodtype_patient);
        adviseEditText = findViewById(R.id.advise_from_doctor);
        saveButton = findViewById(R.id.save_patient);

        // Retrieve the selected patient's information from the intent
        Intent intent = getIntent();
        if (intent != null) {
            Patient selectedPatient = intent.getParcelableExtra("selectedPatient");
            if (selectedPatient != null) {
                // Update the XML layout with the patient's information
                nameTextView.setText("Name: " + selectedPatient.getName());
                ageTextView.setText("Age: " + selectedPatient.getAge());
                genderTextView.setText("Gender: " + selectedPatient.getSex());
                bloodTypeTextView.setText("Bloodtype: " + selectedPatient.getBloodType());
            }
        }

        // You can handle the saveButton click listener as needed
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement save functionality here
                Toast.makeText(getApplicationContext(), "Data saved successfully!", Toast.LENGTH_SHORT).show();
                Intent saveIntent = new Intent(PatientInfoActivity.this, DoctorPageActivity.class);
                PatientInfoActivity.this.startActivity(saveIntent);
            }
        });
    }
}


