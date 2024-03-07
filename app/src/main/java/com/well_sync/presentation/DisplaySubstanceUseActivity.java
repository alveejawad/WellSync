package com.well_sync.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import android.app.Activity;

import com.well_sync.R;

public class DisplaySubstanceUseActivity extends Activity {

    private TextView nameText, amountText;
    private Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_subs);

        // Initialize TextViews
        nameText = findViewById(R.id.substance_name);
        amountText = findViewById(R.id.substance_amount);
        buttonContinue = findViewById(R.id.button_continue_2);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("name");
        String amount = intent.getStringExtra("amount");
        String date = intent.getStringExtra("date");
        nameText.setText("Substance Name: " + name);
        amountText.setText("Amount: " + amount);


        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle close button click
                Intent continueIntent = new Intent(DisplaySubstanceUseActivity.this, HomePageActivity.class);
                continueIntent.putExtra("email",email);
                DisplaySubstanceUseActivity.this.startActivity(continueIntent);
            }
        });
    }
}
