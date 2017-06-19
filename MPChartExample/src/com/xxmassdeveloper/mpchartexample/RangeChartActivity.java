package com.xxmassdeveloper.mpchartexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.RangeChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RangeData;
import com.github.mikephil.charting.data.RangeDataSet;
import com.github.mikephil.charting.data.RangeEntry;
import com.github.mikephil.charting.data.RangeEntryPoint;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.util.ArrayList;

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

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        IAxisValueFormatter xAxisFormatter = new DefaultAxisValueFormatter(0);
        xAxis.setValueFormatter(xAxisFormatter);

        xAxis.resetAxisMaximum();

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(new DefaultAxisValueFormatter(0));
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setAxisMaximum(24f);

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
