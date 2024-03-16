package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.well_sync.R;
import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;

import java.util.Date;

public class MedicationTrackerActivity extends AppCompatActivity {

    private EditText nameMed, amountMed, dosageMed;
    protected String name, amount, dosage;

    private DailyLogHandler dailyLogHandler;
    private String email;
    private DailyLog dailyLog;
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_medication);

        ImageView closeIcon = findViewById(R.id.exit);
        nameMed = findViewById(R.id.name_of_pills);
        amountMed = findViewById(R.id.num_of_pills);
        dosageMed = findViewById(R.id.num_of_dosage);
        Button saveButton = findViewById(R.id.save);

        //get email and date from HomePage Activity
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        String date = intent.getStringExtra("date");

        dailyLogHandler = new DailyLogHandler();
        Date currDate = DailyLogHandler.DateFromString(date);

        // Get the data from patient
        PatientHandler patientHandler = new PatientHandler();
        Patient newPatient = patientHandler.getDetails(email);
        dailyLog = dailyLogHandler.getDailyLog(newPatient,currDate);


        // Set click listeners or any other event listeners as needed
        closeIcon.setOnClickListener(view -> {
            // Handle close button click
            Intent closeIntent = new Intent(MedicationTrackerActivity.this, HomePageActivity.class);
            closeIntent.putExtra("email",email);
            MedicationTrackerActivity.this.startActivity(closeIntent);
        });

        saveButton.setOnClickListener(view -> {
            // Initialize the data from user
            name = nameMed.getText().toString();
            if (name.isEmpty()) {
                // Show a Toast or handle the validation error as needed
                Toast.makeText(MedicationTrackerActivity.this, "Medication name cannot be empty.", Toast.LENGTH_SHORT).show();
                return; // Exit the onClick method to prevent further execution
            }
            amount = amountMed.getText().toString();
            if (amount.isEmpty()) {
                // Show a Toast or handle the validation error as needed
                Toast.makeText(MedicationTrackerActivity.this, "Amount cannot be empty.", Toast.LENGTH_SHORT).show();
                return; // Exit the onClick method to prevent further execution
            }
            dosage = dosageMed.getText().toString();
            if (dosage.isEmpty()) {
                // Show a Toast or handle the validation error as needed
                Toast.makeText(MedicationTrackerActivity.this, "Dosage cannot be empty.", Toast.LENGTH_SHORT).show();
                return; // Exit the onClick method to prevent further execution
            }
            int amountInt = Integer.parseInt(amount);

                dailyLog.addMedication(name, amountInt);
            try {
                dailyLogHandler.setMedication(newPatient,dailyLog);
            } catch (InvalidDailyLogException e) {
                throw new RuntimeException(e);
            }
            Intent saveIntent = new Intent(MedicationTrackerActivity.this, DisplayMedicationActivity.class);
                saveIntent.putExtra("name", name);
                saveIntent.putExtra("amount", amount);
                saveIntent.putExtra("dosage", dosage);
                saveIntent.putExtra("email", email);
                MedicationTrackerActivity.this.startActivity(saveIntent);
        });
    }

}
