package com.well_sync.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;

import com.well_sync.R;
import com.well_sync.objects.Patient;
import com.well_sync.logic.PatientHandler;
import com.well_sync.objects.UserCredentials;


public class UserSettingsActivity extends AppCompatActivity {
    //private TextView doctor;
    private Intent intent;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        ImageView closeIcon = findViewById(R.id.close);
        Button editButton = findViewById(R.id.Editbutton);

        //get email from Sign Up Activity
        intent = getIntent();
        email = intent.getStringExtra("email");

        PatientHandler patientHandler = new PatientHandler();
        Patient patient = patientHandler.getDetails(email);

        setData(R.id.name, patient.getFirstName()+" "+patient.getLastName());
        setData(R.id.birthday, patient.getAge()+"");
        setData(R.id.gender, patient.getSex().name());
        setData(R.id.bloodtype, patient.getBloodType().name());

        closeIcon.setOnClickListener(view -> {
            // Handle close button click
            Intent closeIntent = new Intent(UserSettingsActivity.this, HomePageActivity.class);
            UserSettingsActivity.this.startActivity(closeIntent);
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
    public void setData(int id, String data){
        TextView field = findViewById(id);
        field.setText(data);
    }
}