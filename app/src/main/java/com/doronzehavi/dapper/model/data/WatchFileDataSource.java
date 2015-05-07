package com.doronzehavi.dapper.model.data;

import android.util.Log;

import com.doronzehavi.dapper.common.utils.BusProvider;
import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.common.utils.Utils;
import com.doronzehavi.dapper.model.WatchDataSource;
import com.doronzehavi.dapper.model.entities.Watch;
import com.doronzehavi.dapper.model.entities.WatchesWrapper;

import java.io.IOException;
import java.util.List;

/**
 * This class provides access to the user's saved configurations and components
 */
public class WatchFileDataSource implements WatchDataSource {

    private static List<Watch> mWatches;

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
        if (Utils.userFileExists()){
            try {
                mWatches = Utils.loadWatchesFromFile();
                BusProvider.getDataBusInstance().post(new WatchesWrapper(mWatches));
                Log.d(Constants.TAG, "User watches sent via Data bus");
            } catch (IOException e) {
                Log.e(Constants.TAG, "Error: Unable to load watches from file! - " + e.toString());
                e.printStackTrace();
            }
        } else /* user has never saved any watches */ {
            mWatches = DefaultWatchDataSource.getInstance().createDefaultWatches();
            BusProvider.getDataBusInstance().post(new WatchesWrapper(mWatches));
            Log.d(Constants.TAG, "Default Watches sent via Data bus");
        }
    }


    public void saveWatches(List<Watch> watches) {
        // Get the watches wrapper and then call Constants.saveWatchesToFile()
        Utils.saveWatchesToFile(watches);
    }


}
