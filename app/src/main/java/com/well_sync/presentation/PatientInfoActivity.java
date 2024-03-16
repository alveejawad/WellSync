package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.well_sync.R;
import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;

import java.util.Date;

public class PatientInfoActivity extends AppCompatActivity {
    private DailyLog dailyLog;
    private DailyLogHandler dailyLogHandler;
    private Patient patient;
    private TextView nameTextView, ageTextView, genderTextView, bloodTypeTextView;
    private EditText adviseEditText;
    private Button currentButton, saveButton;
    private String doctorEmail, patientEmail;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        dailyLogHandler = new DailyLogHandler();

        // Initialize views
        adviseEditText = findViewById(R.id.advise_from_doctor);
        currentButton = findViewById(R.id.current_button);
        saveButton = findViewById(R.id.save_patient);

        // Retrieve the selected patient's information from the intent
        Intent intent = getIntent();
        doctorEmail = intent.getStringExtra("doctorEmail");
        date = intent.getStringExtra("date");
        patientEmail = intent.getStringExtra("patientEmail");
        PatientHandler patientHandler = new PatientHandler();
        Patient patient = patientHandler.getDetails(patientEmail);

        setData(R.id.name_patient,"Name: " + patient.getFirstName()+" "+patient.getLastName());
        setData(R.id.age_patient, "Age: " +patient.getAge());
        setData(R.id.gender_patient,"Gender: " +  patient.getSex().name());
        setData(R.id.bloodtype_patient,"Blood Type: " + patient.getBloodType().name());

        // handle the Button click listener as needed
        currentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date currDate=DailyLogHandler.DateFromString(date);
                dailyLog = dailyLogHandler.getDailyLog(patient, currDate);
                if(dailyLog == null) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(PatientInfoActivity.this, "Daily Log is null.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                Date date = dailyLog.getDate();
                Intent currentIntent = new Intent(PatientInfoActivity.this, DailyLogPatientActivity.class);
                currentIntent.putExtra("date", date);
                currentIntent.putExtra("patientEmail",patientEmail);
                currentIntent.putExtra("doctorEmail", doctorEmail);
                PatientInfoActivity.this.startActivity(currentIntent);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement save functionality here
                String advise = adviseEditText.toString();
                if(advise.isEmpty()) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(PatientInfoActivity.this, "Advise can not be empty.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                Toast.makeText(getApplicationContext(), "Data saved successfully!", Toast.LENGTH_SHORT).show();
                Intent saveIntent = new Intent(PatientInfoActivity.this, DoctorPageActivity.class);
                saveIntent.putExtra("email", doctorEmail);
                PatientInfoActivity.this.startActivity(saveIntent);
            }
        });
    }
    public void setData(int id, String data){
        TextView field = findViewById(id);
        field.setText(data);
    }
}




