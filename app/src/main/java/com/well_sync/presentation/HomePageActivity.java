package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageView;
import com.well_sync.R;
import android.app.Activity;

public class HomePageActivity extends Activity {

    private ImageView userAccess;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        // Find your widgets by their IDs
        LinearLayout moodLayout = findViewById(R.id.moodID);
        LinearLayout symptomLayout = findViewById(R.id.symptomID);
        LinearLayout medicationLayout = findViewById(R.id.medicationID);
        LinearLayout substanceLayout = findViewById(R.id.substanceUseID);

        userAccess = findViewById(R.id.user);
        // Set click listeners for each widget
        userAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle close button click
                Intent closeIntent = new Intent(HomePageActivity.this, UserDetailsActivity.class);
                HomePageActivity.this.startActivity(closeIntent);
            }
        });
        // Set click listeners for each widget
        moodLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moodIntent = new Intent(HomePageActivity.this, MoodTrackerActivity.class);
                HomePageActivity.this.startActivity(moodIntent);
            }
        });

        symptomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent symptomIntent = new Intent(HomePageActivity.this, SymptomTrackerActivity.class);
                HomePageActivity.this.startActivity(symptomIntent);
            }
        });

        medicationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicationIntent = new Intent(HomePageActivity.this, MedicationTrackerActivity.class);
                HomePageActivity.this.startActivity(medicationIntent);
            }
        });

        substanceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent substanceUdeIntent = new Intent(HomePageActivity.this, SubstanceUseTrackerActivity.class);
                HomePageActivity.this.startActivity(substanceUdeIntent);
            }
        });
    }
}

