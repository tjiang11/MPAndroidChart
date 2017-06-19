package com.github.mikephil.charting.interfaces.datasets;

import com.github.mikephil.charting.data.RangeEntry;

/**
 * Created by TJiang on 6/19/2017.
 */

public interface IRangeDataSet extends IBarLineScatterCandleBubbleDataSet<RangeEntry> {
    /**
     * Returns the width used for drawing borders around the ranges.
     * If borderWidth == 0, no border will be drawn.
     *
     * @return
     */
    float getBarBorderWidth();

    /**
     * Returns the color drawing borders around the ranges.
     *
     * @return
     */
    int getBarBorderColor();

    /**
     * Returns the alpha value (transparency) that is used for drawing the
     * highlight indicator.
     *
     * @return
     */
    int getHighLightAlpha();

    /**
     * Returns the space that is left out on the left and right side of each
     * candle.
     *
     * @return
     */
    float getBarSpace();


}
