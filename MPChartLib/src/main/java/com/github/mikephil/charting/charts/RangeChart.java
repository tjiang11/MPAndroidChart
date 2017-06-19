package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.data.RangeData;
import com.github.mikephil.charting.interfaces.dataprovider.RangeDataProvider;
import com.github.mikephil.charting.renderer.RangeChartRenderer;

/**
 * Created by TJiang on 6/19/2017.
 */

public class RangeChart extends BarLineChartBase<RangeData> implements RangeDataProvider {

    public RangeChart(Context context) { super(context); }

    public RangeChart(Context context, AttributeSet attrs) {super(context, attrs); }

    public RangeChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();

        mRenderer = new RangeChartRenderer(this, mAnimator, mViewPortHandler);
    }

    @Override
    public RangeData getRangeData() { return mData; }
}
