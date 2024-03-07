package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.well_sync.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomePageActivity extends AppCompatActivity {

    private String email;


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        //get email from Login Activity
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        String date = getCurrentDate();


        // Find your widgets by their IDs
        LinearLayout moodLayout = findViewById(R.id.moodID);
        LinearLayout symptomLayout = findViewById(R.id.symptomID);
        LinearLayout medicationLayout = findViewById(R.id.medicationID);
        LinearLayout substanceLayout = findViewById(R.id.substanceUseID);

        ImageView userAccess = findViewById(R.id.user);
        // Set click listeners for each widget
        userAccess.setOnClickListener(view -> {
            // Handle close button click
            Intent userIntent = new Intent(HomePageActivity.this, UserSettingsActivity.class);
            userIntent.putExtra("email",email);
            HomePageActivity.this.startActivity(userIntent);
        });
        // Set click listeners for each widget
        moodLayout.setOnClickListener(v -> {
            Intent moodIntent = new Intent(HomePageActivity.this, MoodTrackerActivity.class);
            moodIntent.putExtra("email",email);
            moodIntent.putExtra("date",date);
            HomePageActivity.this.startActivity(moodIntent);
        });
        symptomLayout.setOnClickListener(v -> {
            Intent symptomIntent = new Intent(HomePageActivity.this, SymptomsTrackerActivity.class);
            symptomIntent.putExtra("email",email);
            symptomIntent.putExtra("date",date);
            HomePageActivity.this.startActivity(symptomIntent);
        });
        medicationLayout.setOnClickListener(v -> {
            Intent medicationIntent = new Intent(HomePageActivity.this, MedicationTrackerActivity.class);
            medicationIntent.putExtra("email",email);
            medicationIntent.putExtra("date",date);
            HomePageActivity.this.startActivity(medicationIntent);
        });
        substanceLayout.setOnClickListener(v -> {
            Intent substanceIntent = new Intent(HomePageActivity.this, SubstanceUseTrackerActivity.class);
            substanceIntent.putExtra("email",email);
            substanceIntent.putExtra("date",date);
            HomePageActivity.this.startActivity(substanceIntent);
        });
    }
    private String getCurrentDate(){
        //get current date
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        return dateFormat.format(date);
    }
}