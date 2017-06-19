package com.github.mikephil.charting.data;

import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.datasets.IRangeDataSet;

import java.util.List;

/**
 * Created by TJiang on 6/19/2017.
 */

public class RangeDataSet extends LineScatterCandleRadarDataSet<RangeEntry> implements IRangeDataSet {

    public RangeDataSet(List<RangeEntry> rangeEntryPoints, String label) {
        super(rangeEntryPoints, label);
    }

    @Override
    public DataSet<RangeEntry> copy() {
        return null;
    }

    @Override
    public int getHighLightAlpha() {
        return 0;
    }

    @Override
    public float getBarBorderWidth() {
        return 0;
    }

    @Override
    public int getBarBorderColor() {
        return 0;
    }
}
