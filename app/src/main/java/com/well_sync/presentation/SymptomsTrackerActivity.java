package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.well_sync.R;
import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.IDailyLogHandler;
import com.well_sync.logic.IPatientHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;

public class SymptomsTrackerActivity extends AppCompatActivity {
    private IDailyLogHandler dailyLogHandler;
    private String email;
    private DailyLog dailyLog;
    private final String[] symptomsList = getResources().getStringArray(R.array.symptoms);
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_symptomstracker);
        //initialize handler
        dailyLogHandler = new DailyLogHandler();
        //initialize buttons
        ImageView closeIcon = findViewById(R.id.close);
        Button saveButton = findViewById(R.id.savebutton);

        //get email and date from HomePage Activity
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        String date = intent.getStringExtra("date");

        // Get the data from patient
        IPatientHandler patientHandler = new PatientHandler();
        Patient newPatient = patientHandler.getDetails(email);
        dailyLog = dailyLogHandler.getDailyLog(newPatient, date);

        // Set click listeners or any other event listeners as needed
        closeIcon.setOnClickListener(view -> {
            // Handle close button click
            Intent closeIntent = new Intent(SymptomsTrackerActivity.this, HomePageActivity.class);
            closeIntent.putExtra("email",email);
            SymptomsTrackerActivity.this.startActivity(closeIntent);
        });

        saveButton.setOnClickListener(view -> {
            addSymptom(symptomsList[0],R.id.ratingSadness);
            addSymptom(symptomsList[1],R.id.ratingHelplessness);
            addSymptom(symptomsList[2],R.id.ratingSelfEsteem);
            addSymptom(symptomsList[3],R.id.ratingIsolation);
            addSymptom(symptomsList[4], R.id.ratingLowMotivation);
            addSymptom(symptomsList[5],R.id.ratingImpulsivity);
            addSymptom(symptomsList[6],R.id.ratingConcentration);
            addSymptom(symptomsList[7],R.id.ratingAggressiveness);
            addSymptom(symptomsList[8],R.id.ratingGrandioseIdeas);
            addSymptom(symptomsList[9],R.id.ratingRacingThoughts);
            addSymptom(symptomsList[10],R.id.ratingAnxiety);
            addSymptom(symptomsList[11],R.id.ratingSleep);
            addSymptom(symptomsList[12],R.id.ratingHeadache);
            addSymptom(symptomsList[13],R.id.ratingPain);
            addSymptom(symptomsList[14],R.id.ratingAppetite);
            addSymptom(symptomsList[15],R.id.ratingGuilt);
            addSymptom(symptomsList[16],R.id.ratingSuicide);

            try {
                dailyLogHandler.setSymptoms(newPatient,dailyLog);
            } catch (InvalidDailyLogException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            // Handle close button click
            Intent continueIntent = new Intent(SymptomsTrackerActivity.this, HomePageActivity.class);
            continueIntent.putExtra("email",email);
            SymptomsTrackerActivity.this.startActivity(continueIntent);
        });

    }

    private void addSymptom(String name, int idSymptom) {
        RatingBar symptom = findViewById(idSymptom); // Assuming you have a RatingBar in your layout with id "ratingBar"
        int valueSymptom = (int)symptom.getRating();
        try {
            dailyLog.addSymptom(name, valueSymptom);
        } catch (InvalidDailyLogException e) {
            e.printStackTrace();
        }
    }

}

