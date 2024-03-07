package com.well_sync.presentation;
import androidx.appcompat.app.AppCompatActivity;
import com.well_sync.R;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.objects.DailyLog;
import com.well_sync.objects.Patient;
import com.well_sync.objects.Symptom;
import com.well_sync.objects.UserCredentials;
import com.well_sync.logic.UserAuthenticationHandler;
import android.graphics.Paint;

import java.util.Date;
import java.util.List;

public class DailyLogPatientActivity extends AppCompatActivity {

    private TextView viewDate;
    private TextView viewMood, viewSleep, viewNotes, viewSadness, viewHelplessness, viewLowSelfEsteem, viewIsolated, viewMotivation, viewImpulsivity;
    private TextView viewFocus, viewAggressiveness, viewInability, viewRacing, viewAnxiety, viewLowSleep, viewHeadache, viewBodyache, viewAppetite, viewGuilt, viewSuicide;
    private TextView viewPillsName, viewPillsAmount, viewPillsDosage, viewSubsName, viewAmount;
    private Button backButton;
    private Date date;
    private String strMood, strNotess, strSubsName, strPillsName;
    private List<Symptom> symptomList;
    private int  moodScore, sleep, sadness, helplessness, lowSelfEsteem, isolated, motivation, impulsivity, inability, focus, aggresivity;
    private int  racing, anxiety, lowSleep, headache, bodyache, appetite, guilt, suicide, pillsAmount, pillDosage, substanceAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_log_patient);
        //DATE
        viewDate = findViewById(R.id.date);
        //MOOD LOG
        viewMood=findViewById(R.id.mood);
        viewSleep=findViewById(R.id.sleep);
        viewNotes=findViewById(R.id.Notes);

        DailyLogHandler dailyLogHandler = new DailyLogHandler();
        Intent intent = getIntent();
        date = (Date) intent.getSerializableExtra("date");

        //get email and date from HomePage Activity
        String email = intent.getStringExtra("email");
        String doctorEmail = intent.getStringExtra("email");
        // Get the data from patient
        PatientHandler patientHandler = new PatientHandler();
        Patient newPatient = patientHandler.getDetails(email);
        DailyLog dailyLog = dailyLogHandler.getDailyLog(newPatient,date);
        moodScore = dailyLog.getMoodScore();
        sleep = dailyLog.getSleepHours();
        strNotess = dailyLog.getNotes();
        symptomList = dailyLog.getSymptoms();

        strPillsName = intent.getStringExtra("pillname");
        pillsAmount = intent.getIntExtra("pillamount", 1);
        pillDosage = intent.getIntExtra("pilldosage", 1);
        strSubsName = intent.getStringExtra("substancename");
        substanceAmount = intent.getIntExtra("substanceamount", 1);
        //SYMPTOMS LOG
        showSymptom(R.id.Sadness,symptomList.get(0).getIntensity());
        showSymptom(R.id.Helplessness,symptomList.get(1).getIntensity());
        showSymptom(R.id.LowSelfEsteem,symptomList.get(2).getIntensity());
        showSymptom(R.id.Isolation,symptomList.get(3).getIntensity());
        showSymptom(R.id.LowMotivation,symptomList.get(4).getIntensity());
        showSymptom(R.id.Impulsivity,symptomList.get(5).getIntensity());
        showSymptom(R.id.Concentration,symptomList.get(6).getIntensity());
        showSymptom(R.id.Aggressiveness,symptomList.get(7).getIntensity());
        showSymptom(R.id.Inability,symptomList.get(8).getIntensity());
        showSymptom(R.id.RacingThoughts,symptomList.get(9).getIntensity());
        showSymptom(R.id.Anxiety,symptomList.get(10).getIntensity());
        showSymptom(R.id.sleep_problems,symptomList.get(11).getIntensity());
        showSymptom(R.id.Headache,symptomList.get(12).getIntensity());
        showSymptom(R.id.Pain,symptomList.get(13).getIntensity());
        showSymptom(R.id.Appetite,symptomList.get(14).getIntensity());
        showSymptom(R.id.Guilt,symptomList.get(15).getIntensity());
        showSymptom(R.id.Suicide,symptomList.get(16).getIntensity());
        //MEDICATION LOG
        viewPillsName=findViewById(R.id.PillsName);
        viewPillsAmount=findViewById(R.id.PillsAmount);
        viewPillsDosage=findViewById(R.id.PillsDosage);
        //SUBSTANCE LOG
        viewSubsName=findViewById(R.id.SubsName);
        viewAmount=findViewById(R.id.amount);

        viewDate.setText(" " + date);
        viewMood.setText(" " + moodScore);
        viewSleep.setText(" " + sleep);
        viewNotes.setText(" " + strNotess);
        viewPillsName.setText(" " + strPillsName);
        viewPillsAmount.setText(" " + pillsAmount);
        viewPillsDosage.setText(" " + pillDosage);
        viewSubsName.setText(" " + strSubsName);
        viewAmount.setText(" " + substanceAmount);


        //BUTTON
        backButton=findViewById(R.id.backbutton);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent saveIntent = new Intent(DailyLogPatientActivity.this, DoctorPageActivity.class);
                saveIntent.putExtra("email", doctorEmail);
                DailyLogPatientActivity.this.startActivity(saveIntent);
            }
        });

    }
    private void showSymptom(int id,int  index){
           TextView symptom = findViewById(id);
        symptom.setText(" " + index);

    }

}
