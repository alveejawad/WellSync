package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.well_sync.R;
import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;

import java.util.Date;

public class SymptomsTrackerActivity extends AppCompatActivity {
    private DailyLogHandler dailyLogHandler;
    private String email;
    private DailyLog dailyLog;
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

        //Get an object of type Date from the string
        Date currDate = DailyLogHandler.DateFromString(date);

        // Get the data from patient
        PatientHandler patientHandler = new PatientHandler();
        Patient newPatient = patientHandler.getDetails(email);
        dailyLog = dailyLogHandler.getDailyLog(newPatient,currDate);

        // Set click listeners or any other event listeners as needed
        closeIcon.setOnClickListener(view -> {
            // Handle close button click
            Intent closeIntent = new Intent(SymptomsTrackerActivity.this, HomePageActivity.class);
            closeIntent.putExtra("email",email);
            SymptomsTrackerActivity.this.startActivity(closeIntent);
        });

        saveButton.setOnClickListener(view -> {
            addSymptom("Sadness/Despair",R.id.ratingSadness);
            addSymptom("Helplessness/Hopelessness",R.id.ratingHelplessness);
            addSymptom("Low Self-Esteem",R.id.ratingSelfEsteem);
            addSymptom("Feeling isolated",R.id.ratingIsolation);
            addSymptom("Low Motivation/ Loss of Interest", R.id.ratingLowMotivation);
            addSymptom("Impulsivity",R.id.ratingImpulsivity);
            addSymptom("Inability to Concentrate",R.id.ratingConcentration);
            addSymptom("Aggressive Behavior",R.id.ratingAggressiveness);
            addSymptom("Feeling like you can do anything",R.id.ratingGrandioseIdeas);
            addSymptom("Racing Thoughts",R.id.ratingRacingThoughts);
            addSymptom("High Anxiety/Excessive Worry",R.id.ratingAnxiety);
            addSymptom("Sleep Problems",R.id.ratingSleep);
            addSymptom("Headache",R.id.ratingHeadache);
            addSymptom("Body Ache/Pain",R.id.ratingPain);
            addSymptom("Decreased or Increased Appetit",R.id.ratingAppetite);
            addSymptom("Feelings of Guilt or Self-Blame",R.id.ratingGuilt);
            addSymptom("Thoughts of Death or Suicide",R.id.ratingSuicide);
            try {
                dailyLogHandler.setSymptoms(newPatient,dailyLog);
            } catch (InvalidDailyLogException e) {
                throw new RuntimeException(e);
            }

            // Handle close button click
            Intent continueIntent = new Intent(SymptomsTrackerActivity.this, HomePageActivity.class);
            continueIntent.putExtra("email",email);
            SymptomsTrackerActivity.this.startActivity(continueIntent);
        });

    }

    public void addSymptom(String name, int idSymptom){
        RatingBar symptom = findViewById(idSymptom); // Assuming you have a RatingBar in your layout with id "ratingBar"
        int valueSymptom=  (int)symptom.getRating();
        dailyLog.addSymptom(name,valueSymptom);
    }

}

