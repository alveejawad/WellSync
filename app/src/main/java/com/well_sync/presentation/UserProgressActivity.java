package com.well_sync.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.well_sync.R;
import com.well_sync.logic.DailyLogHandler;
import com.well_sync.logic.IDailyLogHandler;
import com.well_sync.logic.IPatientHandler;
import com.well_sync.logic.PatientHandler;
import com.well_sync.objects.Patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserProgressActivity extends AppCompatActivity {
    private String patientEmail, doctorEmail, date;
    private LineChart lineChart;
    private List<String> dates;
    private int sizeXAxis;
    private List<String> moodNames;
    AnyChartView symptomsChart;
    private String[] symptomNames;
    private double[] average= {0.5, 2.3, 3.1, 1.9, 0.7, 3.6, 2.2, 0.4, 1.3, 3.7, 2.8, 1.5, 0.9, 3.2, 1.1, 2.6, 0.2};
    private IDailyLogHandler dailyLogHandler;
    private Patient patient;
    private float[] moodScores, sleepHours, symptomScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_progress);
        // retrieve the patient email and date from the intent
        Intent intent = getIntent();
        patientEmail = intent.getStringExtra("patientEmail");
        doctorEmail = intent.getStringExtra("doctorEmail");
        date = intent.getStringExtra("date");

        //get the handler to call the methods
        IPatientHandler patientHandler = new PatientHandler();
        patient = patientHandler.getDetails("patient1@example.com");
        dailyLogHandler= new DailyLogHandler();

        //Names
        moodNames = Arrays.asList(getResources().getStringArray(R.array.moods));
        symptomNames = getResources().getStringArray(R.array.symptoms);
        //get the data for x and y coordinates
        dates = dailyLogHandler.getAllDatesAsString(patient);
        moodScores=dailyLogHandler.getAllMoodScores(patient);
        sleepHours=dailyLogHandler.getAllSleepHours(patient);
        symptomScores=dailyLogHandler.getAverageSymptoms(patient);
        List<Entry> entriesMood =dailyLogHandler.getEntries(moodScores);
        List<Entry> entriesSleep =dailyLogHandler.getEntries(sleepHours);

        //creat the charts
        createLineChart(R.id.mood_chart,"Mood", dates,moodNames,entriesMood);
        createLineChart(R.id.sleep_chart,"Sleep Hours", dates,null,entriesSleep);
        createPieChart(R.id.symptoms_chart);

        // Set click listeners or any other event listeners as needed
        ImageView  closeImageView = findViewById(R.id.close);
        closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle close button click
                Intent closeIntent = new Intent(UserProgressActivity.this, PatientInfoActivity.class);
                closeIntent.putExtra("doctorEmail",doctorEmail);
                closeIntent.putExtra("patientEmail",patientEmail);
                closeIntent.putExtra("date",date);
                UserProgressActivity.this.startActivity(closeIntent);
            }
        });

    }
    private void createPieChart(int idView) {
        symptomsChart = findViewById(idView);
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        for (int i = 0; i < symptomNames.length; i++) {
            dataEntries.add(new ValueDataEntry(symptomNames[i], symptomScores[i]));
        }
        pie.data(dataEntries);
        symptomsChart.setChart(pie);
    }

    private void createLineChart(int idView, String label, List<String> xList,List<String> yList,List<Entry> entries){
        lineChart = findViewById(idView);
        lineChart.getAxisRight().setDrawLabels(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xList));
        xAxis.setLabelCount(xList.size());
        xAxis.setAxisLineWidth(5f);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setGranularity(1f);

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        if(yList!=null){
            yAxis.setValueFormatter(new IndexAxisValueFormatter(yList));
            yAxis.setAxisMaximum(yList.size()-1);
            yAxis.setLabelCount(yList.size());
        }else{
            yAxis.setAxisMaximum(16);
            yAxis.setLabelCount(8);
        }
        yAxis.setAxisLineWidth(5f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setGranularity(1f);
        LineDataSet dataSet = new LineDataSet(entries,label);
        dataSet.setColor(Color.BLUE);
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

}
