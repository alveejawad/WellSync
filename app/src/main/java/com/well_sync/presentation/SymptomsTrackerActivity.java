package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.well_sync.R;
import com.well_sync.objects.UserCredentials;
import com.well_sync.logic.UserAuthenticationHandler;
import android.graphics.Paint;
import android.widget.RatingBar;


public class SymptomsTrackerActivity extends AppCompatActivity {

    private ImageView closeIcon;
    private RatingBar viewSadness, viewHelplessness, viewLowSelfEsteem, viewIsolated, viewMotivation, viewImpulsivity;
    private RatingBar viewFocus, viewAggressiveness, viewInability, viewRacing, viewAnxiety, viewLowSleep, viewHeadache, viewBodyache, viewAppetite, viewGuilt, viewSuicide;
    private Button saveButton;
    private int  sleep, sadness, helplessness, lowSelfEsteem, isolated, motivation, impulsivity, inability, focus, aggresivity;
    private int  racing, anxiety, lowSleep, headache, bodyache, appetite, guilt, suicide;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_symptomstracker);

        closeIcon = findViewById(R.id.close);
        viewSadness=findViewById(R.id.ratingSadness);
        viewHelplessness=findViewById(R.id.ratingHelplessness);
        viewLowSelfEsteem=findViewById(R.id.ratingSelfEsteem;
        viewIsolated=findViewById(R.id.ratingIsolation);
        viewMotivation=findViewById(R.id.ratingLowMotivation);
        viewImpulsivity=findViewById(R.id.ratingImpulsivity);
        viewFocus=findViewById(R.id.ratingConcentration);
        viewAggressiveness=findViewById(R.id.ratingAggressiveness);
        viewInability=findViewById(R.id.ratingGrandioseIdeas);
        viewRacing=findViewById(R.id.ratingRacingThoughts);
        viewAnxiety=findViewById(R.id.ratingAnxiety);
        viewLowSleep=findViewById(R.id.ratingSleep);
        viewHeadache=findViewById(R.id.ratingHeadache);
        viewBodyache=findViewById(R.id.ratingPain);
        viewAppetite=findViewById(R.id.ratingAppetite);
        viewGuilt=findViewById(R.id.ratingGuilt);
        viewSuicide=findViewById(R.id.ratingSuicide);


        // Set click listeners or any other event listeners as needed
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle close button click
                Intent closeIntent = new Intent(SymptomsTrackerActivity.this, HomePageActivity.class);
                SymptomsTrackerActivity.this.startActivity(closeIntent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle save button click

            }
        });



    }

}

