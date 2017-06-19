package com.github.mikephil.charting.data;

import java.util.ArrayList;

/**
 * Created by TJiang on 6/19/2017.
 */

public class RangeEntry extends Entry {

    private ArrayList<RangeEntryPoint> mRanges;

    public RangeEntry(float x, ArrayList<RangeEntryPoint> ranges) {
        super(x, 0);
        this.mRanges = ranges;
    }

    public void addRange(RangeEntryPoint newEntryPoint) {
        this.mRanges.add(newEntryPoint);
    }

    public RangeEntryPoint getRange(int index) {
        return this.mRanges.get(index);
    }
}
