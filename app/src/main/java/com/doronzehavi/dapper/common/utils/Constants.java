package com.doronzehavi.dapper.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.doronzehavi.dapper.Dapper;
import com.doronzehavi.dapper.R;
import com.doronzehavi.dapper.model.WatchComponent;
import com.doronzehavi.dapper.model.entities.BackgroundComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds constant values.
 */
public class Constants {

    public static final String TAG = "Dapper";
    public static final String USER_DATA_PATH = "udata.ser";
    public static final String KEY_WATCH = "watch";

    /********************
     * Watch components *
     ********************/

    /*
     * Backgrounds
     **************/
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

    /*********************
     *      Watches      *
     ********************/




    // Watch #0
    public static final Map<String, WatchComponent> WATCH_0_BACKGROUND_COMPONENTS;
    static
    {
        WATCH_0_BACKGROUND_COMPONENTS = new HashMap<>();
        WATCH_0_BACKGROUND_COMPONENTS.put(KEY_BACKGROUND_GRAY, MAP_KEY_TO_BG_COMP.get(KEY_BACKGROUND_GRAY));
        WATCH_0_BACKGROUND_COMPONENTS.put(KEY_BACKGROUND_GOLD, MAP_KEY_TO_BG_COMP.get(KEY_BACKGROUND_GOLD));
    }
    public static final String WATCH_0_DEFAULT_BACKGROUND_KEY = KEY_BACKGROUND_GRAY;




    // Watch #1
    public static final Map<String, WatchComponent> WATCH_1_BACKGROUND_COMPONENTS;
    static
    {
        WATCH_1_BACKGROUND_COMPONENTS = new HashMap<>();
        WATCH_1_BACKGROUND_COMPONENTS.put(KEY_BACKGROUND_GRAY, MAP_KEY_TO_BG_COMP.get(KEY_BACKGROUND_GRAY));
        WATCH_1_BACKGROUND_COMPONENTS.put(KEY_BACKGROUND_GOLD, MAP_KEY_TO_BG_COMP.get(KEY_BACKGROUND_GOLD));
    }
    public static final String WATCH_1_DEFAULT_BACKGROUND_KEY = KEY_BACKGROUND_GOLD;

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











    public static final String KEY_BACKGROUND = "key_background";

    // Takes a key, gives a bitmap.
    private static final Map<String, Bitmap> MAP_BACKGROUND = new HashMap<>();


    /**
     * The bitmaps are only initalized when they are first requested.
     */
    public static Bitmap getBackgroundBitmap(String key){
        // 1) If not initialized, initialize it.
        if (!MAP_BACKGROUND.containsKey(key)){
            initBitmapBackground(key);
        }
        return MAP_BACKGROUND.get(key);
    }

    private static Bitmap initBitmapBackground(String key) {
        int res = Constants.MAP_KEY_TO_RES.get(key);
        Bitmap bitmap = BitmapFactory.decodeResource(Dapper.getContext().getResources(),
                res);
        MAP_BACKGROUND.put(key, bitmap);
        return bitmap;
    }
}