package com.github.mikephil.charting.interfaces.dataprovider;

import com.github.mikephil.charting.data.RangeData;

/**
 * Created by TJiang on 6/19/2017.
 */

public interface RangeDataProvider extends BarLineScatterCandleBubbleDataProvider {

    RangeData getRangeData();
}
