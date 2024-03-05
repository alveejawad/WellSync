package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.well_sync.R;

public class MedicationTrackerActivity extends AppCompatActivity {

    private ImageView closeIcon;
    private EditText nameMed, amountMed;
    protected String name, amount;
    private Button saveButton;
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_medication);

        closeIcon = findViewById(R.id.exit);
        nameMed = findViewById(R.id.name_of_pills);
        amountMed = findViewById(R.id.num_of_pills);
        saveButton = findViewById(R.id.save);


        // Set click listeners or any other event listeners as needed
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle close button click
                Intent closeIntent = new Intent(MedicationTrackerActivity.this, HomePageActivity.class);
                MedicationTrackerActivity.this.startActivity(closeIntent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle save button click

                // Initialize the data from user
                name = nameMed.getText().toString();
                amount = amountMed.getText().toString();

                Intent saveIntent = new Intent(MedicationTrackerActivity.this, DisplayMedicationActivity.class);
                saveIntent.putExtra("name", name);
                saveIntent.putExtra("amount", amount);


                MedicationTrackerActivity.this.startActivity(saveIntent);

            }
        });
    }

}
