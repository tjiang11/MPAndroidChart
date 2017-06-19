package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.RangeData;
import com.github.mikephil.charting.data.RangeEntry;
import com.github.mikephil.charting.data.RangeEntryPoint;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.RangeDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IRangeDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by TJiang on 6/19/2017.
 */

public class RangeChartRenderer extends BarLineScatterCandleBubbleRenderer {

    protected RangeDataProvider mChart;

    private float[] mBodyBuffers = new float[4];

    public RangeChartRenderer(RangeDataProvider chart, ChartAnimator animator,
                              ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        this.mChart = chart;
    }

    @Override
    public void initBuffers() {

    }

    @Override
    public void drawData(Canvas c) {
        RangeData rangeData = mChart.getRangeData();

        for (IRangeDataSet set : rangeData.getDataSets()) {
            if (set.isVisible()) {
                drawDataSet(c, set);
            }
        }
    }

    protected void drawDataSet(Canvas c, IRangeDataSet dataSet) {

        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());

        float phaseY = mAnimator.getPhaseY();
        float barSpace = dataSet.getBarSpace();

        mXBounds.set(mChart, dataSet);

        for (int j = mXBounds.min; j <= mXBounds.range + mXBounds.min; j++) {

            RangeEntry e = dataSet.getEntryForIndex(j);

            if (e == null)
                continue;

            final float xPos = e.getX();

            for (RangeEntryPoint rep : e.getRanges()) {
                float close = rep.getClose();
                float open = rep.getOpen();

                mBodyBuffers[0] = xPos - 0.5f + barSpace;
                mBodyBuffers[1] = close * phaseY;
                mBodyBuffers[2] = xPos + 0.5f - barSpace;
                mBodyBuffers[3] = open * phaseY;

                trans.pointValuesToPixel(mBodyBuffers);

                if (close > open) {
                    mRenderPaint.setColor(dataSet.getColor());

                    c.drawRect(mBodyBuffers[0], mBodyBuffers[3],
                               mBodyBuffers[2], mBodyBuffers[1],
                               mRenderPaint);
                }

            }
        }

    }

    @Override
    public void drawValues(Canvas c) {

    }

    @Override
    public void drawExtras(Canvas c) {

    }

    @Override
    public void drawHighlighted(Canvas c, Highlight[] indices) {

    }
}
