package com.well_sync.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.well_sync.R;
import com.well_sync.objects.*;
import com.well_sync.logic.MoodLogHandler;
import java.util.Date;
import java.time.LocalDate;

public class MoodTrackerActivity extends AppCompatActivity {

    private ImageView closeImageView, smileImageView, neutralImageView, angryImageView, sickImageView, selectedImageView;
    private EditText sleepHoursEditText, userNotesEditText;
    private Button saveButton;
    private TextView emotionText;

    private MoodLog moodLog;
    private Patient patient;
    private String email;
    private MoodLogHandler moodLogHandler;

    protected String emotion, sleepHoursText, userNotes;
    private Date date;
    private int moodScores;
    private int sleepHours;
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
                emotionText.setText("Happy - Your mood score is 4");

                // Set the mood score
                moodScores = 4;
            }
        });

        neutralImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetColorFilter();
                neutralImageView.setColorFilter(Color.RED);
                selectedImageView = neutralImageView;
                // Set the emotion text below the selected image view
                emotionText.setText("Neutral - Your mood score is 3");

                // Set the mood score
                moodScores = 3;
            }
        });

        angryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetColorFilter();
                angryImageView.setColorFilter(Color.RED);
                selectedImageView = angryImageView;
                // Set the emotion text below the selected image view
                emotionText.setText("Angry - Your mood score is 2");

                // Set the mood score
                moodScores = 2;
            }
        });

        sickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetColorFilter();
                sickImageView.setColorFilter(Color.RED);
                selectedImageView = sickImageView;
                // Set the emotion text below the selected image view
                emotionText.setText("Sick - Your mood score is 1");

                // Set the mood score
                moodScores = 1;
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
                // Create a LocalDate instance and convert it to date
                LocalDate localDate = LocalDate.now();
                date = java.sql.Date.valueOf(localDate.toString());
                // Initialize the data from user
                emotion = emotionText.getText().toString();
                sleepHoursText = sleepHoursEditText.getText().toString();
                userNotes = userNotesEditText.getText().toString();

                // Get the data from patient
                patient = getPatientDetail(view);
                // Get the data from mood
                moodLog = getMoodLogDetails(view);

                if(moodLog != null) {
                    // Save date to show on the next page
                    Intent saveIntent = new Intent(MoodTrackerActivity.this, DisplayDataActivity.class);
                    saveIntent.putExtra("emotion", emotion);
                    saveIntent.putExtra("sleepHours", sleepHoursText);
                    saveIntent.putExtra("userNotes", userNotes);
                    MoodTrackerActivity.this.startActivity(saveIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Save failed", Toast.LENGTH_SHORT).show();
                }
                // Call the logic layer
                moodLogHandler.setMoodLog(patient, moodLog);
            }
        });
    }

    public Patient getPatientDetail(View view) {
        Patient result;
        email = "test123@umanitoba.ca";
        result = new Patient(email);
        return result;
    }
    public MoodLog getMoodLogDetails(View view) {
        MoodLog result;
        // Create a LocalDate instance and convert it to date
        LocalDate localDate = LocalDate.now();
        date = java.sql.Date.valueOf(localDate.toString());
        emotion = emotionText.getText().toString();
        sleepHoursText = sleepHoursEditText.getText().toString();
        sleepHours = Integer.parseInt(sleepHoursText);
        userNotes = userNotesEditText.getText().toString();
        result = new MoodLog(new Date(), moodScores, sleepHours, userNotes);
        return result;
    }
    private void resetColorFilter() {
        if (selectedImageView != null) {
            selectedImageView.clearColorFilter();
        }
    }
}
