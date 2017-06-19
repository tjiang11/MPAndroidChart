package com.github.mikephil.charting.data;

/**
 * Created by TJiang on 6/19/2017.
 */

public class RangeEntryPoint {

    private float open;
    private float close;

    public RangeEntryPoint(float open, float close) {
        this.open = open;
        this.close = close;
    }

    public float getOpen() {
        return this.open;
    }

    public float getClose() {
        return this.close;
    }
}
