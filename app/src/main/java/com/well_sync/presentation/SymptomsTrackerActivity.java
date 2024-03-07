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
import java.util.ArrayList;

public class SymptomsTrackerActivity extends AppCompatActivity {
    private  ArrayList<Integer> symptomsList = new ArrayList<>();
    private ImageView closeIcon;
    private Button saveButton;
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_symptomstracker);
        closeIcon=findViewById(R.id.close);
        saveButton=findViewById(R.id.savebutton);



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
                addSymptom(R.id.close);
                addSymptom(R.id.ratingSadness);
                addSymptom(R.id.ratingHelplessness);
                addSymptom(R.id.ratingSelfEsteem);
                addSymptom(R.id.ratingIsolation);
                addSymptom(R.id.ratingLowMotivation);
                addSymptom(R.id.ratingImpulsivity);
                addSymptom(R.id.ratingConcentration);
                addSymptom(R.id.ratingAggressiveness);
                addSymptom(R.id.ratingGrandioseIdeas);
                addSymptom(R.id.ratingRacingThoughts);
                addSymptom(R.id.ratingAnxiety);
                addSymptom(R.id.ratingSleep);
                addSymptom(R.id.ratingHeadache);
                addSymptom(R.id.ratingPain);
                addSymptom(R.id.ratingAppetite);
                addSymptom(R.id.ratingGuilt);
                addSymptom(R.id.ratingSuicide);
            }
        });

    }

    public void addSymptom(int idSymptom){
        RatingBar symptom = findViewById(idSymptom); // Assuming you have a RatingBar in your layout with id "ratingBar"
        int valueSymptom=  (int)symptom.getRating();
        symptomsList.add(valueSymptom);
    }

}

