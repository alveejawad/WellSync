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
    private MoodLogHandler moodLogHandler;

    private Date date;
    private int moodScores;
    private int sleepHours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood); // Replace with your actual layout file name

        // Create a LocalDate instance and convert it to date
        LocalDate localDate = LocalDate.now();
        date = java.sql.Date.valueOf(localDate.toString());

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
                emotionText.setText("Neutral");

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
                emotionText.setText("Angry");

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
                emotionText.setText("Sick");

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

                // Handle save button click
                String emotion = emotionText.getText().toString();
                String sleepHoursText = sleepHoursEditText.getText().toString();
                // Convert sleepHours to an integer
                try {
                    sleepHours = Integer.parseInt(sleepHoursText);
                } catch (NumberFormatException e) {
                    // Handle the case where th input is not a valid integer
                    e.printStackTrace();
                }
                String userNotes = userNotesEditText.getText().toString();
                // Handle the object MoodLog
                moodLog.setSleepHours(sleepHours);
                moodLog.setDate(date);
                moodLog.setMoodScore(moodScores);
                moodLog.setNotes(userNotes);
                // Save date to show on the next page
                Intent saveIntent = new Intent(MoodTrackerActivity.this, DisplayDataActivity.class);
                saveIntent.putExtra("emotion", emotion);
                saveIntent.putExtra("sleepHours", sleepHoursText);
                saveIntent.putExtra("userNotes", userNotes);
                MoodTrackerActivity.this.startActivity(saveIntent);
            }
        });
    }
    private void resetColorFilter() {
        if (selectedImageView != null) {
            selectedImageView.clearColorFilter();
        }
    }
}
