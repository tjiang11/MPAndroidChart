package com.xxmassdeveloper.mpchartexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.RangeChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RangeData;
import com.github.mikephil.charting.data.RangeDataSet;
import com.github.mikephil.charting.data.RangeEntry;
import com.github.mikephil.charting.data.RangeEntryPoint;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by TJiang on 6/19/2017.
 */

public class RangeChartActivity extends DemoBase implements SeekBar.OnSeekBarChangeListener {

    protected RangeChart mChart;
    private SeekBar seekBar;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rangechart);

        mChart = (RangeChart) findViewById(R.id.rangeChart);
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView = (TextView) findViewById(R.id.textView1);

        seekBar.setOnSeekBarChangeListener(this);

        mChart.getDescription().setEnabled(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        IAxisValueFormatter xAxisFormatter = new DefaultAxisValueFormatter(0);
        xAxis.setValueFormatter(xAxisFormatter);

        xAxis.resetAxisMaximum();

        final YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setTextSize(14.0f);
        leftAxis.setLabelCount(8, false);

        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int militaryHour = (int) Math.round(Math.floor(value));
                int displayHour = militaryHour;
                String amPm;
                String minute;

                if (displayHour == 0) {
                    displayHour = 12;
                }

                if (displayHour > 12) {
                    displayHour -= 12;
                }

                if (militaryHour >= 12 && militaryHour != 24) {
                    amPm = "PM";
                } else {
                    amPm = "AM";
                }

                if (value - Math.floor(value) == 0.5) {
                    minute = ":30";
                } else if (value - Math.floor(value) == 0.25) {
                    minute = ":15";
                } else if (value - Math.floor(value) == 0.75) {
                    minute = ":45";
                } else if (value - Math.floor(value) == 0.0){
                    minute = "";
                } else {
                    return "";
                }

                return displayHour + minute + amPm;
            }
        });

        leftAxis.setInverted(true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setAxisMaximum(24f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setGranularity(1f);
        leftAxis.setGridLineWidth(1.5f);

        mChart.setOnChartGestureListener(new OnChartGestureListener() {

            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartLongPressed(MotionEvent me) {

            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {

            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
                ViewPortHandler handler = mChart.getViewPortHandler();
                MPPointD topLeft = mChart.getValuesByTouchPoint(handler.contentLeft(), handler.contentTop(), YAxis.AxisDependency.LEFT);
                MPPointD bottomRight = mChart.getValuesByTouchPoint(handler.contentRight(), handler.contentBottom(), YAxis.AxisDependency.LEFT);

                double visibleRange = bottomRight.y - topLeft.y;

                if (visibleRange < 2.0) {
                    leftAxis.setGranularity(.25f);
                } else if (visibleRange < 4.2) {
                    leftAxis.setGranularity(.5f);
                } else {
                    leftAxis.setGranularity(1f);
                }
            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {

            }
        });

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        setData(10);
    }

    private void setData(int numEntries) {

        ArrayList<RangeEntry> rangeEntriesBlue = new ArrayList<>();
        ArrayList<RangeEntry> rangeEntriesRed = new ArrayList<>();
        ArrayList<RangeEntry> rangeEntriesGreen = new ArrayList<>();

        for (int i = 0; i < numEntries; i++) {

            ArrayList<RangeEntryPoint> entryPointsBlue = new ArrayList<>();
            ArrayList<RangeEntryPoint> entryPointsRed = new ArrayList<>();
            ArrayList<RangeEntryPoint> entryPointsGreen = new ArrayList<>();

            for (int j = 0; j < 3; j++) {

                float start = (float) Math.random() * 24;
                float duration = (float) Math.random() * 5;
                float end = start + duration;

                entryPointsBlue.add(new RangeEntryPoint(start, end));
            }

            for (int j = 0; j < 3; j++) {

                float start = (float) Math.random() * 20;
                float duration = (float) Math.random() * 5;
                float end = start + duration;

                entryPointsRed.add(new RangeEntryPoint(start, end));
            }

            for (int j = 0; j < 3; j++) {

                float start = (float) Math.random() * 20;
                float duration = (float) Math.random() * 5;
                float end = start + duration;

                entryPointsGreen.add(new RangeEntryPoint(start, end));
            }


            RangeEntry rangeEntryBlue = new RangeEntry(i, entryPointsBlue);
            RangeEntry rangeEntryRed = new RangeEntry(i, entryPointsRed);
            RangeEntry rangeEntryGreen = new RangeEntry(i, entryPointsGreen);

            rangeEntriesBlue.add(rangeEntryBlue);
            rangeEntriesRed.add(rangeEntryRed);
            rangeEntriesGreen.add(rangeEntryGreen);
        }

        RangeDataSet rangeDataSetBlue = new RangeDataSet(rangeEntriesBlue, "Blue");
        rangeDataSetBlue.setColor(Color.BLUE);
        RangeDataSet rangeDataSetRed = new RangeDataSet(rangeEntriesRed, "Red");
        rangeDataSetRed.setColor(Color.RED);
        RangeDataSet rangeDataSetGreen = new RangeDataSet(rangeEntriesGreen, "Green");
        rangeDataSetGreen.setColor(Color.GREEN);

        // Later data sets are drawn over earlier ones.
        RangeData rangeData = new RangeData(
                rangeDataSetBlue,
                rangeDataSetRed,
                rangeDataSetGreen);

        mChart.setData(rangeData);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        textView.setText(String.valueOf(seekBar.getProgress() + 1));
        setData(seekBar.getProgress() + 1);
        mChart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }
}
