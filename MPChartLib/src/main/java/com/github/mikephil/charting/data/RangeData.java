package com.github.mikephil.charting.data;

import com.github.mikephil.charting.interfaces.datasets.IRangeDataSet;

import java.util.List;

/**
 * Created by TJiang on 6/19/2017.
 */

public class RangeData extends BarLineScatterCandleBubbleData<IRangeDataSet> {

    public RangeData() { super(); }

    public RangeData(List<IRangeDataSet> dataSets) { super(dataSets); }

    public RangeData(IRangeDataSet... dataSets) {
        super(dataSets);
    }
}
