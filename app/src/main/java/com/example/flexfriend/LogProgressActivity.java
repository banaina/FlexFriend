package com.example.flexfriend;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LogProgressActivity extends AppCompatActivity implements View.OnClickListener {
    /* LogProgressActivity class:
     * Allows user to log their weight throughout their fitness jouney and view this information
     * in graph format with the help of the PlotPointValues class.
     *
     * References used:
     * https://www.youtube.com/watch?v=KIW4Vp8mjLo&t=431s
     */

//    private XYPlot weightPlot;
//    private LineAndPointFormatter seriesFormat;
//    XYSeries series;
//    private PointsGraphSeries<DataPoint> xySeries;
//    private GraphView weightPlot;
//    private ArrayList<PlotPointValues> plotpts;
//    private ArrayList<Integer> plotPts, numbers;

    private EditText userWeight, userDate;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_progress);

        //initialize UI elements
        userWeight = (EditText) findViewById(R.id.userLogText);
        userDate = (EditText) findViewById(R.id.userDateText);
        addButton = (Button) findViewById(R.id.addWeight);
        addButton.setOnClickListener(this);
        //initialize info variables
//        plotpts = new ArrayList<PlotPointValues>();
//        initGraph();









//        weightPlot = (XYPlot) findViewById(R.id.plot);
//        //initialize information variables
//        plotPts = new ArrayList<Integer>();
//        plotPts.add(1);
//        numbers = new ArrayList<Integer>();
//        numbers.add(1);
//        numbers.add(2);
//        numbers.add(3);
    }

//    public void initGraph(){
//        xySeries = new PointsGraphSeries<>();
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //make sure the text boxes are not empty
//                if(!userWeight.getText().toString().equals("")
//                        && userDate.getText().toString().equals("")){
//                    //make sure the date format is entered correct by the user
//                    if ((userDate.getText().toString().charAt(2) == '/')
//                        && userDate.getText().toString().charAt(4) == '/') {
//                        //read user input
//                        String[] userDateString = userDate.getText().toString().split("/");
//                        double x = Double.parseDouble(userWeight.getText().toString());
//                        double y = Double.parseDouble(userDateString[2]);
//                        Log.d("Add to Graph",
//                                "onClick: Adding a new point. (x,y): (" + x + "," + y + ")" );
//                        plotpts.add(new PlotPointValues(x,y));
//                        initGraph();
//
//                    } else {
//                        userDate.setText("Invalid Format");
//                    }
//
//                }else {
//                    Toast.makeText(LogProgressActivity.this, "Fill both fields", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    @Override
    public void onClick(View v) {
        //when add button is clicked, it adds the new weight into the array and displays it on
        //the graph
        if (v.getId() == R.id.addWeight) {
            if (!userWeight.getText().toString().equals("")) {

















////                Toast.makeText(this, "Adding a new weight point", Toast.LENGTH_SHORT).show();
//
//                //grab the weight value that the user has entered
//                int weight = Integer.parseInt(userWeight.getText().toString());
//                plotPts.add(weight);
//                series = new SimpleXYSeries(plotPts,
//                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Weight");
//                seriesFormat = new LineAndPointFormatter(
//                        Color.RED, Color.GREEN, null, null);
//                seriesFormat.setInterpolationParams(new CatmullRomInterpolator.Params(10,
//                        CatmullRomInterpolator.Type.Centripetal));
//                weightPlot.addSeries(series, seriesFormat);
//                PanZoom.attach(weightPlot);
//                weightPlot.addSeries(series, seriesFormat);
//
//                weightPlot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
//                    @Override
//                    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
//                        int i = Math.round( ((Number)obj).floatValue() );
//                        //specify the Y values measured on grpah
//                        return toAppendTo.append(numbers.get(i));
//                    }
//                    @Override
//                    public Object parseObject(String source, ParsePosition pos) {
//                        return null;
//                    }
//                });
            }
        }
    }
}