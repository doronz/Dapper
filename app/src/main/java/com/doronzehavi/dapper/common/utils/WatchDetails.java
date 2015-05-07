package com.doronzehavi.dapper.common.utils;

import com.doronzehavi.dapper.R;
import com.doronzehavi.dapper.model.WatchComponent;
import com.doronzehavi.dapper.model.entities.BackgroundComponent;
import com.doronzehavi.dapper.model.entities.WatchHandComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by D on 5/6/2015.
 */
public class WatchDetails {
    /****************************************
     *            Watch components          *
     ****************************************/

    ////////////// Backgrounds
    // Keys
    public static final String KEY_BACKGROUND_GRAY = "background_gray";
    public static final String KEY_BACKGROUND_GOLD = "background_gold";
    // Resources
    public static final int RES_BACKGROUND_GRAY = R.drawable.bg;
    public static final int RES_BACKGROUND_GOLD = R.drawable.bg2;
    // Key to resource map
    public static final Map<String, Integer> MAP_KEY_TO_RES;


    static
    {
        MAP_KEY_TO_RES = new HashMap<>();
        MAP_KEY_TO_RES.put(KEY_BACKGROUND_GRAY, RES_BACKGROUND_GRAY);
        MAP_KEY_TO_RES.put(KEY_BACKGROUND_GOLD, RES_BACKGROUND_GOLD);
    }
    public static final Map<String, BackgroundComponent> MAP_KEY_TO_BG_COMP;
    static
    {
        MAP_KEY_TO_BG_COMP = new HashMap<>();
        MAP_KEY_TO_BG_COMP.put(KEY_BACKGROUND_GRAY, new BackgroundComponent(KEY_BACKGROUND_GRAY));
        MAP_KEY_TO_BG_COMP.put(KEY_BACKGROUND_GOLD, new BackgroundComponent(KEY_BACKGROUND_GOLD));
    }

    ////////////// WatchHands
    // Keys
    public static final String KEY_WATCHHAND_STANDARD = "watchhand_standard";
    public static final Map<String, WatchHandComponent> MAP_KEY_TO_WATCHHAND_COMP;
    static
    {
        MAP_KEY_TO_WATCHHAND_COMP = new HashMap<>();
        MAP_KEY_TO_WATCHHAND_COMP.put(KEY_WATCHHAND_STANDARD, new WatchHandComponent(KEY_WATCHHAND_STANDARD));
    }

    /****************************************
     *                Watches               *
     ****************************************/
    //// Watch #0 \\\\
    public static final Map<String, WatchComponent> WATCH_0_BACKGROUND_COMPONENTS;
    static
    {
        WATCH_0_BACKGROUND_COMPONENTS = new HashMap<>();
        WATCH_0_BACKGROUND_COMPONENTS.put(KEY_BACKGROUND_GRAY, MAP_KEY_TO_BG_COMP.get(KEY_BACKGROUND_GRAY));
        WATCH_0_BACKGROUND_COMPONENTS.put(KEY_BACKGROUND_GOLD, MAP_KEY_TO_BG_COMP.get(KEY_BACKGROUND_GOLD));
    }
    public static final String WATCH_0_DEFAULT_BACKGROUND_KEY = KEY_BACKGROUND_GRAY;
    public static final String WATCH_0_DEFAULT_WATCHHAND_KEY = KEY_WATCHHAND_STANDARD;

    //// Watch #1 \\\\
    public static final Map<String, WatchComponent> WATCH_1_BACKGROUND_COMPONENTS;
    static
    {
        WATCH_1_BACKGROUND_COMPONENTS = new HashMap<>();
        WATCH_1_BACKGROUND_COMPONENTS.put(KEY_BACKGROUND_GRAY, MAP_KEY_TO_BG_COMP.get(KEY_BACKGROUND_GRAY));
        WATCH_1_BACKGROUND_COMPONENTS.put(KEY_BACKGROUND_GOLD, MAP_KEY_TO_BG_COMP.get(KEY_BACKGROUND_GOLD));
    }
    public static final String WATCH_1_DEFAULT_BACKGROUND_KEY = KEY_BACKGROUND_GOLD;
    public static final String WATCH_1_DEFAULT_WATCHHAND_KEY = KEY_WATCHHAND_STANDARD;


    /**
     * Array of background components for each watch
     */
    public static final Map<String, WatchComponent>[] BACKGROUND_OPTIONS;
    static
    {
        BACKGROUND_OPTIONS = new HashMap[2];
        BACKGROUND_OPTIONS[0] = WATCH_0_BACKGROUND_COMPONENTS;
        BACKGROUND_OPTIONS[1] = WATCH_1_BACKGROUND_COMPONENTS;
    }

}
