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

    private EditText userWeight;
    private Button addButton;
    private XYPlot weightPlot;
    private LineAndPointFormatter seriesFormat;
    private ArrayList<Integer> plotPts;
    XYSeries series;
    private Number[] domain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_progress);

        //initialize UI elements
        userWeight = (EditText) findViewById(R.id.userLogText);
        weightPlot = (XYPlot) findViewById(R.id.plot);
        addButton = (Button) findViewById(R.id.addWeight);
        addButton.setOnClickListener(this);

        //initialize information variables
        plotPts = new ArrayList<Integer>();
        plotPts.add(1);
        plotPts.add(1);
//        plotPts.add(9);
        final Number[] domain = {1,2,3,6,7,8,9,10,13,14};

        //grab the weight value that the user has entered
//        userWeight.getText().toString();
//        int weight = Integer.valueOf(userWeight.getText().toString());
//        plotPts.add(weight);

        //add points to graph and specify the appearance of the graph
        series = new SimpleXYSeries(plotPts,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Weight");
        seriesFormat = new LineAndPointFormatter(
                Color.RED, Color.GREEN, null, null);

        seriesFormat.setInterpolationParams(new CatmullRomInterpolator.Params(10,
                CatmullRomInterpolator.Type.Centripetal));

        weightPlot.addSeries(series, seriesFormat);

        weightPlot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round( ((Number)obj).floatValue() );
                //specify the Y values measured on grpah
                return toAppendTo.append(domain[i]);
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });

        PanZoom.attach(weightPlot);
    }

    @Override
    public void onClick(View v) {
        //when add button is clickked, it adds the new weight into the array and displays it on
        //the graph
        if (v.getId() == R.id.addWeight) {
            if (!userWeight.getText().toString().equals("")) {
//                Toast.makeText(this, "Adding a new weight point", Toast.LENGTH_SHORT).show();

                //grab the weight value that the user has entered
                int weight = Integer.parseInt(userWeight.getText().toString());
                plotPts.add(weight);
                series = new SimpleXYSeries(plotPts,
                        SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Weight");
                weightPlot.addSeries(series, seriesFormat);
//
//                //add points to graph and specify the appearance of the graph
//                XYSeries series = new SimpleXYSeries(plotPts,
//                        SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Weight");
//                LineAndPointFormatter seriesFormat = new LineAndPointFormatter(
//                        Color.RED, Color.GREEN, null, null);
//
//                seriesFormat.setInterpolationParams(new CatmullRomInterpolator.Params(10,
//                        CatmullRomInterpolator.Type.Centripetal));
//
//                weightPlot.addSeries(series, seriesFormat);
//
//                weightPlot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
//                    @Override
//                    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
//                        int i = Math.round( ((Number)obj).floatValue() );
//                        //specify the Y values measured on grpah
//                        return toAppendTo.append(domain[i]);
//                    }
//
//                    @Override
//                    public Object parseObject(String source, ParsePosition pos) {
//                        return null;
//                    }
//                });
//
//                PanZoom.attach(weightPlot);

            }
        }
    }
}