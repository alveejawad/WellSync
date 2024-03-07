package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.well_sync.R;
import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.*;

import java.util.Date;

public class MedicationTrackerActivity extends AppCompatActivity {

    private ImageView closeIcon;
    private EditText nameMed, amountMed, dosageMed;
    protected String name, amount, dosage;

    private Medication medication;
    private MedicationValidator medicationValidator;
    private Button saveButton;
    private DailyLogHandler dailyLogHandler;
    private String email, date;
    private DailyLog dailyLog;
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_medication);

        closeIcon = findViewById(R.id.exit);
        nameMed = findViewById(R.id.name_of_pills);
        amountMed = findViewById(R.id.num_of_pills);
        dosageMed = findViewById(R.id.num_of_dosage);
        saveButton = findViewById(R.id.save);

        //get email and date from HomePage Activity
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        date = intent.getStringExtra("date");
        dailyLogHandler = new DailyLogHandler();
        Date currDate = dailyLogHandler.DateFromString(date);

        // Get the data from patient
        PatientHandler patientHandler = new PatientHandler();
        Patient newPatient = patientHandler.getDetails(email);
        dailyLog = dailyLogHandler.getDailyLog(newPatient,currDate);


        // Set click listeners or any other event listeners as needed
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle close button click
                Intent closeIntent = new Intent(MedicationTrackerActivity.this, HomePageActivity.class);
                closeIntent.putExtra("email",email);
                MedicationTrackerActivity.this.startActivity(closeIntent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle save button click

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
                if (amountInt < 0 || amountInt > 5) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(MedicationTrackerActivity.this, "Invalid amount; must be between 0 and 5 inclusive.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                    dailyLog.addMedication(name, amountInt);
                    Intent saveIntent = new Intent(MedicationTrackerActivity.this, DisplayMedicationActivity.class);
                    saveIntent.putExtra("name", name);
                    saveIntent.putExtra("amount", amount);
                    saveIntent.putExtra("dosage", dosage);
                    saveIntent.putExtra("email", email);
                    MedicationTrackerActivity.this.startActivity(saveIntent);
            }
        });
    }

}
