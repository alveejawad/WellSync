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
import com.well_sync.logic.DoctorHandler;
import com.well_sync.logic.IDoctorHandler;
import com.well_sync.logic.IPatientHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.logic.exceptions.InvalidDoctorException;
import com.well_sync.logic.exceptions.InvalidPatientException;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Doctor;
import com.well_sync.objects.Patient;

public class PatientInfoActivity extends AppCompatActivity {
    private DailyLog dailyLog;
    private Patient patient;
    private Doctor doctor;
    private TextView nameTextView, ageTextView, genderTextView, bloodTypeTextView;
    private EditText adviseEditText;
    private Button logsButton, sendButton, deleteButton;
    private String doctorEmail, patientEmail;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        // Initialize views
        adviseEditText = findViewById(R.id.advise_from_doctor);
        logsButton = findViewById(R.id.daily_logs);
        sendButton = findViewById(R.id.send_rec);
        deleteButton = findViewById(R.id.delete_patient);


        // Retrieve the selected patient's information from the intent
        Intent intent = getIntent();
        doctorEmail = intent.getStringExtra("doctorEmail");
        date = intent.getStringExtra("date");
        patientEmail = intent.getStringExtra("patientEmail");
        IPatientHandler patientHandler = new PatientHandler();
        patient = patientHandler.getDetails(patientEmail);
        IDoctorHandler doctorHandler = new DoctorHandler();
        doctor = doctorHandler.getDetails(doctorEmail);

        setData(R.id.name,patient.getFirstName()+" "+patient.getLastName());
        setData(R.id.birthday, String.valueOf(patient.getAge()));
        setData(R.id.gender,patient.getSex().name());
        setData(R.id.bloodtype,patient.getBloodType().name());

        // handle the Button click listener as needed
        logsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dailyLog = new DailyLog(date, 3, 8, "ok babe");
                        //dailyLogHandler.getDailyLog(patient, currDate);
                if(dailyLog == null) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(PatientInfoActivity.this, "Daily Log is null.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                Intent currentIntent = new Intent(PatientInfoActivity.this, DateActivity.class);
                currentIntent.putExtra("date", date);
                currentIntent.putExtra("patientEmail",patientEmail);
                currentIntent.putExtra("doctorEmail", doctorEmail);
                PatientInfoActivity.this.startActivity(currentIntent);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement save functionality here
                String advise = adviseEditText.getText().toString();
                if(advise.isEmpty()) {
                    // Show a Toast or handle the validation error as needed
                    Toast.makeText(PatientInfoActivity.this, "Advise can not be empty.", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }
                patient.setDoctorNotes(advise);
                try {
                    patientHandler.editPatientDetails(patient);
                } catch (InvalidPatientException e) {
                    Toast.makeText(getApplicationContext(), "Changes weren't saved, try again later.", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), "Data saved successfully!", Toast.LENGTH_SHORT).show();
                Intent saveIntent = new Intent(PatientInfoActivity.this, DoctorPageActivity.class);
                saveIntent.putExtra("email", doctorEmail);
                PatientInfoActivity.this.startActivity(saveIntent);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    doctorHandler.removePatient(doctor, patient);
                    Toast.makeText(getApplicationContext(), "Patient removed successfully!", Toast.LENGTH_SHORT).show();
                } catch (InvalidDoctorException | InvalidPatientException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
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




