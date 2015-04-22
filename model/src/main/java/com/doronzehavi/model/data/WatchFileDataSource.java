package com.doronzehavi.model.data;

import com.doronzehavi.common.utils.BusProvider;
import com.doronzehavi.model.WatchDataSource;
import com.doronzehavi.model.entities.Watch;
import com.doronzehavi.model.entities.WatchesWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D on 4/21/2015.
 */
public class WatchFileDataSource implements WatchDataSource {

    private static WatchFileDataSource INSTANCE;
    private WatchFileDataSource() {
    }
    public static WatchFileDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WatchFileDataSource();
        }

        return INSTANCE;
    }

    /**
     * This is where the watches get loaded from file.
     * TODO...
     */
    @Override
    public void getWatches() {
        List<Watch> watches = new ArrayList<Watch>();
        watches.add(new Watch("gray", "bg"));
        watches.add(new Watch("gold", "bg2"));
        WatchesWrapper response = new WatchesWrapper(watches);
        BusProvider.getDataBusInstance().post(response);
    }
}
