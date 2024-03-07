package com.well_sync.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.well_sync.objects.*;
import com.well_sync.persistence.IDailyLogPersistence;

import androidx.appcompat.app.AppCompatActivity;

import com.well_sync.R;
import com.well_sync.objects.Patient;
import com.well_sync.persistence.stubs.DailyLogPersistenceStub;

public class PatientInfoActivity extends AppCompatActivity {
    private IDailyLogPersistence dailyLogPersistence;
    private List<Medication> medicationList;
    private List<Substance> substanceList;
    private DailyLog dailyLog = new DailyLog();
    private Patient patient;

    private TextView nameTextView, ageTextView, genderTextView, bloodTypeTextView;
    private EditText adviseEditText;
    private Button currentButton, saveButton;
    private int moodScore, sleepHours, pillsAmount, pillsDosage, subAmount;
    private String moodNotes, pillsName, substanceName;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        dailyLogPersistence = new DailyLogPersistenceStub();
        medicationList = new ArrayList<>();
        substanceList = new ArrayList<>();
        // Initialize views
        nameTextView = findViewById(R.id.name_patient);
        ageTextView = findViewById(R.id.age_patient);
        genderTextView = findViewById(R.id.gender_patient);
        bloodTypeTextView = findViewById(R.id.bloodtype_patient);
        adviseEditText = findViewById(R.id.advise_from_doctor);
        currentButton = findViewById(R.id.current_button);
        saveButton = findViewById(R.id.save_patient);

        // Retrieve the selected patient's information from the intent
        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("patient");
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 1);
        String gender = intent.getStringExtra("sex");
        String bloodType = intent.getStringExtra("bloodtype");
        nameTextView.setText("Name: " + name);
        ageTextView.setText("Age: " + age);
        genderTextView.setText("Gender: " + gender);
        bloodTypeTextView.setText("BloodType: " + bloodType);

        // handle the Button click listener as needed
        currentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dailyLogPersistence.setDailyLog(patient, dailyLog);
                date = dailyLog.getDate();
                moodScore = dailyLog.getMoodScore();
                sleepHours = dailyLog.getSleepHours();
                moodNotes = dailyLog.getNotes();

                //medicationList = dailyLog.getMedications();
                pillsName = "Tylenol, Melatonin, Cough syrup";
                pillsAmount = 5;
                pillsDosage = 5;

                //substanceList = dailyLog.getSubstances();
                substanceName = "Alcohol, Caffiene";
                subAmount = 2;


                Intent currentIntent = new Intent(PatientInfoActivity.this, DailyLogPatientActivity.class);
                currentIntent.putExtra("date", date);
                currentIntent.putExtra("moodscore", moodScore);
                currentIntent.putExtra("sleephour", sleepHours);
                currentIntent.putExtra("moodnote", moodNotes);
                currentIntent.putExtra("pillname", pillsName);
                currentIntent.putExtra("pillamount", pillsAmount);
                currentIntent.putExtra("pilldosage", pillsDosage);
                currentIntent.putExtra("substancename", substanceName);
                currentIntent.putExtra("substanceamount", subAmount);
                PatientInfoActivity.this.startActivity(currentIntent);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement save functionality here
                String advise = adviseEditText.toString();
                if(advise == null) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(PatientInfoActivity.this, "Advise can not be empty.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                Toast.makeText(getApplicationContext(), "Data saved successfully!", Toast.LENGTH_SHORT).show();
                Intent saveIntent = new Intent(PatientInfoActivity.this, DoctorPageActivity.class);
                PatientInfoActivity.this.startActivity(saveIntent);
            }
        });
    }
}


