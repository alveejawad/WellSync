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

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
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
    private int sizeXAxis;
    private List<String> moodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_progress);
        dates = Arrays.asList("12-03", "12-05", "12-06","12-07");
        moodsList = Arrays.asList(getResources().getStringArray(R.array.moods));

        List<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0f, 1f));
        entries1.add(new Entry(1f, 0f));
        entries1.add(new Entry(2f, 3f));
        entries1.add(new Entry(3f, 2f));

        createLineChart(R.id.mood_chart,"Mood", dates,moodsList,entries1);
        createLineChart(R.id.sleep_chart,"Sleep Hours", dates,moodsList,entries1);

    }
    public void createLineChart(int idView, String label, List<String> xList,List<String> yList,List<Entry> entries){
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
        yAxis.setValueFormatter(new IndexAxisValueFormatter(yList));
        yAxis.setAxisMaximum(yList.size()-1);
        yAxis.setLabelCount(yList.size());
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
