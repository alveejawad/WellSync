package com.well_sync.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.well_sync.R;


public class MoodTrackerActivity extends AppCompatActivity {

    private ImageView closeImageView, smileImageView, neutralImageView, angryImageView, sickImageView, selectedImageView;
    private EditText sleepHoursEditText, userNotesEditText;
    private Button saveButton;
    private TextView emotionText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood); // Replace with your actual layout file name

        // Initialize views
        closeImageView = findViewById(R.id.close);
        smileImageView = findViewById(R.id.smile);
        neutralImageView = findViewById(R.id.neutral);
        angryImageView = findViewById(R.id.angry);
        sickImageView = findViewById(R.id.sick);
        emotionText = findViewById(R.id.emotions);
        sleepHoursEditText = findViewById(R.id.sleep_hours);
        userNotesEditText = findViewById(R.id.user_notes);
        saveButton = findViewById(R.id.button);

        // Set click listeners for icon selection
        smileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset color filter for all image views
                resetColorFilter();
                // Set color filter for the selected image view
                smileImageView.setColorFilter(Color.RED);
                selectedImageView = smileImageView;

                // Set the emotion text below the selected image view
                emotionText.setText("Happy");

            }
        });

        neutralImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetColorFilter();
                neutralImageView.setColorFilter(Color.RED);
                selectedImageView = neutralImageView;
                // Set the emotion text below the selected image view
                emotionText.setText("Neutral");
            }
        });

        angryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetColorFilter();
                angryImageView.setColorFilter(Color.RED);
                selectedImageView = angryImageView;
                // Set the emotion text below the selected image view
                emotionText.setText("Angry");
            }
        });

        sickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetColorFilter();
                sickImageView.setColorFilter(Color.RED);
                selectedImageView = sickImageView;
                // Set the emotion text below the selected image view
                emotionText.setText("Sick");
            }
        });
        // Set click listeners or any other event listeners as needed
        closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle close button click
                Intent closeIntent = new Intent(MoodTrackerActivity.this, HomePageActivity.class);
                MoodTrackerActivity.this.startActivity(closeIntent);
            }
        });

        // Similar listeners can be added for other ImageViews, EditTexts, and Button

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle save button click
                String emotion = emotionText.getText().toString();
                String sleepHours = sleepHoursEditText.getText().toString();
                String userNotes = userNotesEditText.getText().toString();
                Intent saveIntent = new Intent(MoodTrackerActivity.this, DisplayDataActivity.class);
                saveIntent.putExtra("emotion", emotion);
                saveIntent.putExtra("sleepHours", sleepHours);
                saveIntent.putExtra("userNotes", userNotes);
                MoodTrackerActivity.this.startActivity(saveIntent);
                // Perform actions with sleepHours and userNotes (e.g., save to database)
            }
        });
    }
    private void resetColorFilter() {
        if (selectedImageView != null) {
            selectedImageView.clearColorFilter();
        }
    }
}
