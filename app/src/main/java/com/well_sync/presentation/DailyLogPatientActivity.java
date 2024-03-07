package com.well_sync.presentation;
import androidx.appcompat.app.AppCompatActivity;
import com.well_sync.R;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.well_sync.objects.UserCredentials;
import com.well_sync.logic.UserAuthenticationHandler;
import android.graphics.Paint;

import java.util.Date;

public class DailyLogPatientActivity extends AppCompatActivity {

    private TextView viewDate;
    private TextView viewMood, viewSleep, viewNotes, viewSadness, viewHelplessness, viewLowSelfEsteem, viewIsolated, viewMotivation, viewImpulsivity;
    private TextView viewFocus, viewAggressiveness, viewInability, viewRacing, viewAnxiety, viewLowSleep, viewHeadache, viewBodyache, viewAppetite, viewGuilt, viewSuicide;
    private TextView viewPillsName, viewPillsAmount, viewPillsDosage, viewSubsName, viewAmount;
    private Button backButton;
    private Date date;
    private String strMood, strNotess, strSubsName, strPillsName;
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
        //SYMPTOMS LOG
        viewSadness=findViewById(R.id.Sadness);
        viewHelplessness=findViewById(R.id.Helplessness);
        viewLowSelfEsteem=findViewById(R.id.LowSelfEsteem);
        viewIsolated=findViewById(R.id.Isolation);
        viewMotivation=findViewById(R.id.LowMotivation);
        viewImpulsivity=findViewById(R.id.Impulsivity);
        viewFocus=findViewById(R.id.Concentration);
        viewAggressiveness=findViewById(R.id.Aggressiveness);
        viewInability=findViewById(R.id.Inability);
        viewRacing=findViewById(R.id.RacingThoughts);
        viewAnxiety=findViewById(R.id.Anxiety);
        viewLowSleep=findViewById(R.id.sleep_problems);
        viewHeadache=findViewById(R.id.Headache);
        viewBodyache=findViewById(R.id.Pain);
        viewAppetite=findViewById(R.id.Appetite);
        viewGuilt=findViewById(R.id.Guilt);
        viewSuicide=findViewById(R.id.Suicide);
        //MEDICATION LOG
        viewPillsName=findViewById(R.id.PillsName);
        viewPillsAmount=findViewById(R.id.PillsAmount);
        viewPillsDosage=findViewById(R.id.PillsDosage);
        //SUBSTANCE LOG
        viewSubsName=findViewById(R.id.SubsName);
        viewAmount=findViewById(R.id.amount);

        Intent intent = getIntent();
        date = (Date) intent.getSerializableExtra("date");
        moodScore = intent.getIntExtra("moodscore", 1);
        sleep = intent.getIntExtra("sleephour", 1);
        strNotess = intent.getStringExtra("moodnote");
        strPillsName = intent.getStringExtra("pillname");
        pillsAmount = intent.getIntExtra("pillamount", 1);
        pillDosage = intent.getIntExtra("pilldosage", 1);
        strSubsName = intent.getStringExtra("substancename");
        substanceAmount = intent.getIntExtra("substanceamount", 1);

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
                startActivity(new Intent(DailyLogPatientActivity.this,DoctorPageActivity.class));
            }
        });

    }


}
