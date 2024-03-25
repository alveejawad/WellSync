/*package com.well_sync.presentation;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.well_sync.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class UserProgressActivity extends AppCompatActivity {
    private LineChart lineChart;
    private List<String> dates;
    private final String[] symptomsList = getResources().getStringArray(R.array.symptoms);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_progress);

        lineChart = findViewById(R.id.chart);
        Description description = new Description();
        description.setText("Mood Progress");
        description.setPosition(150f,15f);
        lineChart.setDescription(description);
        lineChart.getAxisRight().setDrawLabels(false);
        dates = Arrays.asList("12-03","12-05","12-06","12-07");


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dates));
        xAxis.setLabelCount(4);
        xAxis.setGranularity(1f);



        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setValueFormatter(new IndexAxisValueFormatter(symptomsList));
        yAxis.setGranularity(1f);
        yAxis.setLabelCount(4);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(4f); // Assuming mood labels count is 5
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);

       /* YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(19);

        List<Entry> entries1= new ArrayList<>();
        entries1.add(new Entry(0f,0f));
        entries1.add(new Entry(1f,1f));
        entries1.add(new Entry(2f, 2f));
        entries1.add(new Entry( 3f,3f));

        LineDataSet dataSet = new LineDataSet(entries1,"Mood");
        dataSet.setColor(Color.BLUE);
        LineData lineData= new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();





    }
}*/
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



        IPatientHandler patientHandler = new PatientHandler();
        patient = patientHandler.getDetails("patient1@example.com");
        dailyLogHandler= new DailyLogHandler();
        dates = dailyLogHandler.getAllDatesAsString(patient);
        moodNames = Arrays.asList(getResources().getStringArray(R.array.moods));
        symptomNames = getResources().getStringArray(R.array.symptoms);
        moodScores=dailyLogHandler.getAllMoodScores(patient);
        sleepHours=dailyLogHandler.getAllSleepHours(patient);
        symptomScores=dailyLogHandler.getAverageSymptoms(patient);

        List<Entry> entries1 = new ArrayList<>();
        for(int i=0;i<moodScores.length;i++){
            entries1.add(new Entry(i, moodScores[i]-1));
        }

        List<Entry> entries2 = new ArrayList<>();
        for(int i=0;i<sleepHours.length;i++){
            entries2.add(new Entry(i, sleepHours[i]));
        }


        createLineChart(R.id.mood_chart,"Mood", dates,moodNames,entries1);
        createLineChart(R.id.sleep_chart,"Sleep Hours", dates,null,entries2);
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
