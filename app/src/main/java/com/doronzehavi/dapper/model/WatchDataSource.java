package com.doronzehavi.dapper.model;

import android.graphics.Bitmap;

import java.util.Map;


public interface WatchDataSource {

    /**
     * Must be able to load up the watches.
     */
    public void getWatches();

    /**
     * Needs access to configuration options such as background bitmaps
     * @return all the background options available
     */
    public Map<String, Bitmap> getBackgrounds();

}
