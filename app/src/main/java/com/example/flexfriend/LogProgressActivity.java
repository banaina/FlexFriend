package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PanZoom;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;

public class LogProgressActivity extends AppCompatActivity implements View.OnClickListener {

    private XYPlot weightPlot;
    private LineAndPointFormatter seriesFormat;
    XYSeries series;
    private EditText userWeight;
    private Button addButton;
    private ArrayList<Integer> plotPts, numbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_progress);

        //initialize UI elements
        userWeight = (EditText) findViewById(R.id.userLogText);
        addButton = (Button) findViewById(R.id.addWeight);
        addButton.setOnClickListener(this);
        weightPlot = (XYPlot) findViewById(R.id.plot);

        //initialize information variables
        plotPts = new ArrayList<Integer>();
        plotPts.add(1);
        numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Override
    public void onClick(View v) {
        //when add button is clicked, it adds the new weight into the array and displays it on
        //the graph
        if (v.getId() == R.id.addWeight) {
            if (!userWeight.getText().toString().equals("")) {

//                Toast.makeText(this, "Adding a new weight point", Toast.LENGTH_SHORT).show();

                //grab the weight value that the user has entered
                int weight = Integer.parseInt(userWeight.getText().toString());
                plotPts.add(weight);
                series = new SimpleXYSeries(plotPts,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Weight");
                seriesFormat = new LineAndPointFormatter(
                        Color.RED, Color.GREEN, null, null);
                seriesFormat.setInterpolationParams(new CatmullRomInterpolator.Params(10,
                        CatmullRomInterpolator.Type.Centripetal));
                weightPlot.addSeries(series, seriesFormat);
                PanZoom.attach(weightPlot);
                weightPlot.addSeries(series, seriesFormat);

                weightPlot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
                    @Override
                    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                        int i = Math.round( ((Number)obj).floatValue() );
                        //specify the Y values measured on grpah
                        return toAppendTo.append(numbers.get(i));
                    }
                    @Override
                    public Object parseObject(String source, ParsePosition pos) {
                        return null;
                    }
                });
            }
        }
    }
}