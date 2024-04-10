package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class LogProgressActivity extends AppCompatActivity implements View.OnClickListener {
    /* LogProgressActivity class:
     * Allows user to log their weight throughout their fitness jouney and view this information
     * in graph format with the help of the PlotPointValues class.
     *
     * References used:
     * https://www.youtube.com/watch?v=KIW4Vp8mjLo&t=431s
     * https://github.com/PhilJay/MPAndroidChart/tree/master/MPChartExample
     */

    private EditText userWeight, userDate;
    private Button addButton;
    private ImageButton backBtn;
    private LineChart lineChart;
    private XAxis xAxis;
    private ArrayList<Entry> values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_progress);

        //initialize UI elements
        userWeight = (EditText) findViewById(R.id.userLogText);
        userDate = (EditText) findViewById(R.id.userDateText);
        addButton = (Button) findViewById(R.id.addWeight);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        addButton.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        //initialize graph info/variables
        values = new ArrayList<>();

        lineChart = findViewById(R.id.chart);
        // enable touch gestures
        lineChart.setTouchEnabled(true);
        // set listeners
        lineChart.setDrawGridBackground(false);

        Description description = new Description();
        description.setText("Weight");
        description.setPosition(150f,2010f);
        lineChart.setDescription(description);
        lineChart.getAxisRight().setDrawLabels(false);

        //set X axis appearance
        xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);

        //set Y axis appearance
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(500f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setScrollContainer(true);
        // zoom along both axis
        lineChart.setPinchZoom(true);

        //initialize and access info from shared prefs
        SharedPreferences prefs = getSharedPreferences("weight info", MODE_PRIVATE);
        if (prefs!=null){
            Map<String, ?> allEntries = prefs.getAll();
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
                setData(Integer.parseInt(entry.getKey()), (int) entry.getValue());
            }
        }
    }



    private void setData(int x, float y) {
        values.add(new Entry(x, y, getResources().getDrawable(R.drawable.baseline_watch_later_24)));

        LineDataSet dataSet1;
        if (lineChart.getData() != null &&
            lineChart.getData().getDataSetCount() > 0) {
            dataSet1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            dataSet1.setValues(values);
            dataSet1.notifyDataSetChanged();
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            //otherwise create a new dataset
            dataSet1 = new LineDataSet(values, "Weight in Pounds");

            dataSet1.setDrawIcons(false);

            // draw dashed line
            dataSet1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            dataSet1.setColor(Color.BLACK);
            dataSet1.setCircleColor(Color.BLACK);

            // line thickness and point size
            dataSet1.setLineWidth(1f);
            dataSet1.setCircleRadius(3f);

            // draw points as solid circles
            dataSet1.setDrawCircleHole(false);

            // customize legend entry
            dataSet1.setFormLineWidth(1f);
            dataSet1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            dataSet1.setFormSize(15.f);

            // text size of values
            dataSet1.setValueTextSize(9f);

            // draw selection line as dashed
            dataSet1.enableDashedHighlightLine(10f, 5f, 0f);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(dataSet1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            lineChart.setData(data);
            lineChart.invalidate();
        }
    }


    @Override
    public void onClick(View v) {
        //when add button is clicked, it adds the new weight into the array and displays it on
        //the graph
        if (v.getId() == R.id.addWeight) {
            //make sure the text boxes are not empty
            if(!userWeight.getText().toString().equals("")
                    && !userDate.getText().toString().equals("")){
                //make sure the date format is entered correct by the user
                if ((userDate.getText().toString().length() == 10)
                    && (userDate.getText().toString().charAt(2) == '/')
                    && userDate.getText().toString().charAt(5) == '/') {
                    //read user input
                    String[] userDateString = userDate.getText().toString().split("/");
                    int y = Integer.parseInt(userWeight.getText().toString());
                    int x = Integer.parseInt(userDateString[0]);
                    Log.d("Add to Graph",
                            "onClick: Adding a new point. (x,y): (" + x + "," + y + ")" );
                    setData(x,y);
                    SharedPreferences.Editor editor = getSharedPreferences("weight info", MODE_PRIVATE).edit();
                    editor.putInt(String.valueOf(x),y);
                    editor.apply();
                    Toast.makeText(this, "tap the graph to view changes", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogProgressActivity.this, "invalid format", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LogProgressActivity.this, "Fill both fields", Toast.LENGTH_SHORT).show();
            }
        }

        if (v.getId() == R.id.backBtn){
            Intent intent = new Intent(this, ProgressActivity.class);
            startActivity(intent);
        }
    }
}