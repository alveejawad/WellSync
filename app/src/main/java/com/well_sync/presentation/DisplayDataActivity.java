package com.well_sync.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import android.app.Activity;

import com.well_sync.R;

public class DisplayDataActivity extends Activity {

    private TextView emotionsText, sleepHoursText, userNotesText;
    private Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        // Initialize TextViews
        emotionsText = findViewById(R.id.emotions_result);
        sleepHoursText = findViewById(R.id.sleep_hours_result);
        userNotesText = findViewById(R.id.note_result);
        buttonContinue = findViewById(R.id.button_continue);

        Intent intent = getIntent();
        String emotion = intent.getStringExtra("emotion");
        String sleepHours = intent.getStringExtra("sleepHours");
        String userNotes = intent.getStringExtra("userNotes");
        emotionsText.setText("Emotion: " + emotion);
        sleepHoursText.setText("Sleep Hours: " + sleepHours);
        userNotesText.setText("User Notes: " + userNotes);

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle close button click
                Intent continueIntent = new Intent(DisplayDataActivity.this, HomePageActivity.class);
                DisplayDataActivity.this.startActivity(continueIntent);
            }
        });
    }
}
