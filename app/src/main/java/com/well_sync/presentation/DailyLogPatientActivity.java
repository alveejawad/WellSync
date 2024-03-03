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

public class DailyLogPatientActivity extends AppCompatActivity {
    private TextView viewMood, viewSleep, viewNotes, viewSadness, viewHelplessness, viewLowSelfEsteem, viewIsolated, viewMotivation, viewImpulsivity;
    private TextView viewFocus, viewAggressiveness, viewInability, viewRacing, viewAnxiety, viewLowSleep, viewHeadache, viewBodyache, viewAppetite, viewGuilt, viewSuicide;
    private TextView viewPillsName, viewPillsAmount, viewPillsDosage, viewSubsName, viewAmount;
    private Button backButton;

    private String strMood, strNotess, strSubsName, strPillsName, strPillsDosage, strAmount;
    private int  sleep, sadness, helplessness, lowSelfEsteem, isolated, motivation, impulsivity, inability, focus, aggresivity;
    private int  racing, anxiety, lowSleep, headache, bodyache, appetite, guilt, suicide, pillsAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_log_patient);
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
        //BUTTON
        backButton=findViewById(R.id.backbutton);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DailyLogPatientActivity.this,HomePageActivity.class));
            }
        });

    }


}