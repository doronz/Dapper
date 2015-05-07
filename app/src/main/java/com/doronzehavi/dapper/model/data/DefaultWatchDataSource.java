package com.doronzehavi.dapper.model.data;

import com.doronzehavi.dapper.common.utils.BusProvider;
import com.doronzehavi.dapper.model.WatchDataSource;
import com.doronzehavi.dapper.model.WatchFactory;
import com.doronzehavi.dapper.model.entities.Watch;

import java.util.List;

/**
 * This data source returns the default configuration for each watch.
 */
public class DefaultWatchDataSource implements WatchDataSource {
    @Override
    public void getWatches() {
        BusProvider.getDataBusInstance().post(createDefaultWatches());
    }
    private static DefaultWatchDataSource INSTANCE; // Singleton
    private DefaultWatchDataSource() {
    }
    public static DefaultWatchDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DefaultWatchDataSource();
        }
        return INSTANCE;
    }

    public List<Watch> createDefaultWatches()  {
        return WatchFactory.getInstance().getDefaultWatches();
    }
}
