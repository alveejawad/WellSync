package com.well_sync.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import com.well_sync.R;
import com.well_sync.objects.Patient;


public class UserSettingsActivity extends AppCompatActivity {
    private ImageView closeIcon;
    private Button editButton;
    private Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        closeIcon=findViewById(R.id.close);
        editButton=findViewById(R.id.Editbutton);

        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle close button click
                Intent closeIntent = new Intent(UserSettingsActivity.this, HomePageActivity.class);
                UserSettingsActivity.this.startActivity(closeIntent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle close button click
                Intent editIntent = new Intent(UserSettingsActivity.this, UserDetailsActivity.class);
                UserSettingsActivity.this.startActivity(editIntent);
            }
        });
    }

}