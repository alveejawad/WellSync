package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.well_sync.R;
import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.*;

import java.util.Date;

public class SubstanceUseTrackerActivity extends AppCompatActivity {

    private ImageView closeIcon;
    private EditText nameSub, amountSub;
    protected String name, amount;

    private Substance substance;
    private Button saveButton;
    private DailyLogHandler dailyLogHandler;
    private String email, date;
    private DailyLog dailyLog;
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_substance_use);

        closeIcon = findViewById(R.id.exit_1);
        nameSub = findViewById(R.id.name_of_subs);
        amountSub = findViewById(R.id.num_of_subs);
        saveButton = findViewById(R.id.save_1);

        //get email and date from HomePage Activity
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        date = intent.getStringExtra("date");
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
                Intent closeIntent = new Intent(SubstanceUseTrackerActivity.this, HomePageActivity.class);
                closeIntent.putExtra("email",email);
                SubstanceUseTrackerActivity.this.startActivity(closeIntent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle save button click

                // Initialize the data from user
                name = nameSub.getText().toString();
                if (name.isEmpty()) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(SubstanceUseTrackerActivity.this, "Substance name cannot be empty.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                amount = amountSub.getText().toString();
                if (amount.isEmpty()) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(SubstanceUseTrackerActivity.this, "Substance amount cannot be empty.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }

                int amountInt = Integer.parseInt(amount);
                if (amountInt < 0 || amountInt > 5) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(SubstanceUseTrackerActivity.this, "Invalid amount; must be between 0 and 5 inclusive.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }

                substance = new Substance(name, amountInt);

                try {
                    SubstanceValidator.validateSubstance(substance);
                    Intent saveIntent = new Intent(SubstanceUseTrackerActivity.this, DisplaySubstanceUseActivity.class);
                    saveIntent.putExtra("email",email);
                    saveIntent.putExtra("name", name);
                    saveIntent.putExtra("amount", amount);
                    SubstanceUseTrackerActivity.this.startActivity(saveIntent);
                } catch (InvalidDailyLogException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
