package com.doronzehavi.dapper.model.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.doronzehavi.dapper.Dapper;
import com.doronzehavi.dapper.common.utils.BusProvider;
import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.model.WatchDataSource;
import com.doronzehavi.dapper.model.entities.Watch;
import com.doronzehavi.dapper.model.entities.WatchesWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides access to the user's watches and their components
 */
public class WatchFileDataSource implements WatchDataSource {

    private static final Map<String, Bitmap> MAP_BACKGROUND = loadBackgrounds();
    private static Map<String, Bitmap> loadBackgrounds(){
        HashMap<String, Bitmap> backgrounds = new HashMap<>();
        // Gray
        Bitmap BITMAP_BACKGROUND_GRAY = BitmapFactory.decodeResource(Dapper.getContext().getResources(), Constants.RES_BACKGROUND_GRAY);

        // Gold
        Bitmap BITMAP_BACKGROUND_GOLD = BitmapFactory.decodeResource(Dapper.getContext().getResources(), Constants.RES_BACKGROUND_GOLD);

        // Put all
        backgrounds.put(Constants.KEY_BACKGROUND_GRAY, BITMAP_BACKGROUND_GRAY);
        backgrounds.put(Constants.KEY_BACKGROUND_GOLD, BITMAP_BACKGROUND_GOLD);
        return backgrounds;
    }

    public Map<String, Bitmap> getBackgrounds() {
        return MAP_BACKGROUND;
    }

    public Bitmap getBackgroundBitmap(String key){
        return MAP_BACKGROUND.get(key);
    }

    private static WatchFileDataSource INSTANCE; // Singleton
    private WatchFileDataSource() {
    }
    public static WatchFileDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WatchFileDataSource();
        }

        return INSTANCE;
    }

    /**
     * Loads watches for the user if they saved some, or default ones if they didn't.
     */
    @Override
    public void getWatches() {
        BusProvider.getDataBusInstance().post(createDefaultWatches());
    }

    public WatchesWrapper createDefaultWatches()  {
        List<Watch> watches = new ArrayList<>();
        watches.add(new Watch(Constants.KEY_BACKGROUND_GRAY));
        watches.add(new Watch(Constants.KEY_BACKGROUND_GOLD));
        return new WatchesWrapper(watches);
    }
}
