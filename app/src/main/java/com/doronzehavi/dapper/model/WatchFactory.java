package com.doronzehavi.dapper.model;

import com.doronzehavi.dapper.common.utils.WatchDetails;
import com.doronzehavi.dapper.model.entities.Watch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class only builds the watches in a generic way. They are defined in Constants.
 */
public class WatchFactory {

    private Map<Integer, Watch> mWatchMap = new HashMap<>();

    private WatchFactory(){
        mWatchMap.put(0, BASIC_WATCH);
        mWatchMap.put(1, GOLD_WATCH);
    }

    public List<Watch> getDefaultWatches(){
        List<Watch> watches = new ArrayList<>();
        watches.add(BASIC_WATCH);
        watches.add(GOLD_WATCH);
        return watches;
    }

    // TODO: Find a better place for these constants
    /**
     * Basic Watch
     * - Position: 0
     * - Compatible backgrounds: bg
     */
    Watch BASIC_WATCH = new Watch(0, WatchDetails.WATCH_0_DEFAULT_BACKGROUND_KEY, WatchDetails.WATCH_0_DEFAULT_WATCHHAND_KEY);

    /**
     * Gold Watch
     * - Position: 1
     * - Compatible backgrounds: bg2
     */
    Watch GOLD_WATCH = new Watch(1, WatchDetails.WATCH_1_DEFAULT_BACKGROUND_KEY, WatchDetails.WATCH_1_DEFAULT_WATCHHAND_KEY);


    private static WatchFactory INSTANCE;
    public static WatchFactory getInstance(){
        if (INSTANCE == null)
            INSTANCE = new WatchFactory();
        return INSTANCE;
    }
}
