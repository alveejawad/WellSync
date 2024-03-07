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
import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.exceptions.InvalidDailyLogException;
import com.well_sync.objects.*;
import com.well_sync.logic.DailyLogHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MoodTrackerActivity extends AppCompatActivity {

    private ImageView closeImageView, smileImageView, neutralImageView, angryImageView, sickImageView, selectedImageView;
    private EditText sleepHoursEditText, userNotesEditText;
    private Button saveButton;
    private TextView emotionText;

    private DailyLog dailyLog;
    private Patient patient;
    private String email;
    private String date;
    private DailyLogHandler dailyLogHandler;

    protected String emotion, sleepHoursText, userNotes;
    private Intent intent;
    private int moodScores;
    private int sleepHours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood); // Replace with your actual layout file name
        dailyLogHandler = new DailyLogHandler();
        moodScores = 0;

        //get email and date from HomePage Activity
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        date = intent.getStringExtra("date");
        Date currDate = dailyLogHandler.DateFromString(date);

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
                smileImageView.setColorFilter(Color.BLUE);
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
                neutralImageView.setColorFilter(Color.BLUE);
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
                angryImageView.setColorFilter(Color.BLUE);
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
                sickImageView.setColorFilter(Color.BLUE);
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
                closeIntent.putExtra("email",email);
                MoodTrackerActivity.this.startActivity(closeIntent);
            }
        });

        // Similar listeners can be added for other ImageViews, EditTexts, and Button

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize the data from user
                emotion = emotionText.getText().toString();
                // check if emotion is empty before parsing
                if (emotion.isEmpty()) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(MoodTrackerActivity.this, "Emotion cannot be empty.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                sleepHoursText = sleepHoursEditText.getText().toString();
                // check if sleepHoursText is empty before parsing
                if (sleepHoursText.isEmpty()) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(MoodTrackerActivity.this, "Sleep hours cannot be empty.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                userNotes = userNotesEditText.getText().toString();
                // check if userNotes is empty before parsing
                if (userNotes.isEmpty()) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(MoodTrackerActivity.this, "User notes cannot be empty.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                sleepHours = Integer.parseInt(sleepHoursText);

                // Validate mood score before setting it in the dailylog
                if (moodScores < 1 || moodScores > 4) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(MoodTrackerActivity.this, "Invalid mood score; must be between 1 and 4 inclusive.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                // Validate sleep hours before setting it in the dailylog
                if (sleepHours < 0 || sleepHours > 16) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(MoodTrackerActivity.this, "Invalid sleep hours; must be between 0 and 16 inclusive.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }


                DailyLog dailyLog = new DailyLog(currDate,moodScores,sleepHours,userNotes );
                // Get the data from patient
                PatientHandler patientHandler = new PatientHandler();
                Patient newPatient = patientHandler.getDetails(email);

                try {
                    // Get the data from mood
                    dailyLogHandler.setDailyLog(newPatient, dailyLog);
                    Intent saveIntent = new Intent(MoodTrackerActivity.this, DisplayDataActivity.class);
                    saveIntent.putExtra("email", email);
                    saveIntent.putExtra("emotion", emotion);
                    saveIntent.putExtra("sleepHours", sleepHoursText);
                    saveIntent.putExtra("userNotes", userNotes);
                    MoodTrackerActivity.this.startActivity(saveIntent);
                } catch (InvalidDailyLogException e) {
                    Toast.makeText(getApplicationContext(), "Save error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public DailyLog getMoodLogDetails(View view) {
        DailyLog result;
        emotion = emotionText.getText().toString();
        sleepHoursText = sleepHoursEditText.getText().toString();
        sleepHours = Integer.parseInt(sleepHoursText);
        userNotes = userNotesEditText.getText().toString();
        result = new DailyLog(dailyLogHandler.DateFromString(date), moodScores, sleepHours, userNotes);
        return result;
    }
    private void resetColorFilter() {
        if (selectedImageView != null) {
            selectedImageView.clearColorFilter();
        }
    }

}


