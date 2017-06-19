package com.xxmassdeveloper.mpchartexample;

import android.os.Bundle;
import android.support.annotation.Nullable;

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

public class RangeChartActivity extends DemoBase {

    protected RangeChart mChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rangechart);

        mChart = (RangeChart) findViewById(R.id.range_chart);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        IAxisValueFormatter xAxisFormatter = new DefaultAxisValueFormatter(0);
        xAxis.setValueFormatter(xAxisFormatter);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(new DefaultAxisValueFormatter(0));
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        setData();
    }

    private void setData() {
        ArrayList<RangeEntryPoint> entryPoints = new ArrayList<>();

        RangeEntryPoint r1 = new RangeEntryPoint(0.2f, 0.5f);
        RangeEntryPoint r2 = new RangeEntryPoint(0.65f, 0.7f);

        entryPoints.add(r1);
        entryPoints.add(r2);

        RangeEntry rangeEntry = new RangeEntry(0, entryPoints);

        ArrayList<RangeEntry> s1 = new ArrayList<>();
        s1.add(rangeEntry);

        RangeDataSet rangeDataSet = new RangeDataSet(s1, "set 1");

        RangeData rangeData = new RangeData(rangeDataSet);

        mChart.setData(rangeData);
    }
}
