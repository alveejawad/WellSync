package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.well_sync.R;

public class SubstanceUseTrackerActivity extends AppCompatActivity {

    private ImageView closeIcon;
    private EditText nameSub, amountSub;
    protected String name, amount;
    private Button saveButton;
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_substance_use);

        closeIcon = findViewById(R.id.exit_1);
        nameSub = findViewById(R.id.name_of_subs);
        amountSub = findViewById(R.id.num_of_subs);
        saveButton = findViewById(R.id.save_1);


        // Set click listeners or any other event listeners as needed
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle close button click
                Intent closeIntent = new Intent(SubstanceUseTrackerActivity.this, HomePageActivity.class);
                SubstanceUseTrackerActivity.this.startActivity(closeIntent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle save button click

                // Initialize the data from user
                name = nameSub.getText().toString();
                amount = amountSub.getText().toString();

                Intent saveIntent = new Intent(SubstanceUseTrackerActivity.this, DisplaySubstanceUseActivity.class);
                saveIntent.putExtra("name", name);
                saveIntent.putExtra("amount", amount);

                SubstanceUseTrackerActivity.this.startActivity(saveIntent);

            }
        });
    }

}
