package com.well_sync.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.well_sync.R;
import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;

import java.util.Date;
import java.util.List;

public class DateActivity extends AppCompatActivity {

    private RecyclerView dateList;
    private String patientEmail;
    private String doctorEmail;
    private String date;
    private List<Date> listOfDate;

    private PatientHandler patientHandler;
    private Patient patient;
    private DailyLogHandler logHandler;

    private DateAdapter dateAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_date_list);

        dateList = findViewById(R.id.date_list);

        // retrieve the patient email and date from the intent
        Intent intent = getIntent();
        patientEmail = intent.getStringExtra("patientEmail");
        doctorEmail = intent.getStringExtra("doctorEmail");
        date = intent.getStringExtra("date");

        // Initialize handler
        patientHandler = new PatientHandler();
        patient = patientHandler.getDetails(patientEmail);

        // get all the date from the patient
        logHandler = new DailyLogHandler();
        listOfDate = logHandler.getAllDates(patient);

        //show list of dates
        RecyclerView recyclerView = findViewById(R.id.date_list);
        dateAdapter = new DateAdapter(this, listOfDate);
        dateList.setAdapter(dateAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // set a click listener for RecycleView items
        dateAdapter.setOnItemClickListener(position -> {
            Date selectedDate = listOfDate.get(position);
            // Start the DailyLogPatientActivity and pass the selected patient's information
            Intent dateIntent = new Intent(DateActivity.this, DailyLogPatientActivity.class);
            //patient's information
            dateIntent.putExtra("patientEmail",patientEmail);
            //doctor's information
            dateIntent.putExtra("doctorEmail", doctorEmail);
            dateIntent.putExtra("date", selectedDate.toString());
            DateActivity.this.startActivity(dateIntent);
        });
    }
}
