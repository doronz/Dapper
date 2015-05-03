package com.doronzehavi.dapper.model.data;

import android.util.Log;

import com.doronzehavi.dapper.common.utils.BusProvider;
import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.model.WatchDataSource;

/**
 * This class provides access to the user's saved configurations and components
 * TODO: Remove loading of components from this class
 */
public class WatchFileDataSource implements WatchDataSource {


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
        if (false /* user has saved watches */){
            Log.d(Constants.TAG, "User watches sent via Data bus");
        } else /* user has never saved any watches */ {
            BusProvider.getDataBusInstance().post(DefaultWatchDataSource.getInstance().createDefaultWatches());
            Log.d(Constants.TAG, "Default Watches sent via Data bus");
        }
    }


}
