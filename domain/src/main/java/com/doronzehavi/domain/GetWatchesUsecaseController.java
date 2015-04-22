package com.doronzehavi.domain;

import com.doronzehavi.common.utils.BusProvider;
import com.doronzehavi.model.WatchDataSource;
import com.doronzehavi.model.entities.WatchesWrapper;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Implementation of (@link GetWatchesUsecase}
 */
public class GetWatchesUsecaseController implements GetWatchesUsecase {
    private final Bus mUiBus;
    private final WatchDataSource mDataSource;


    // Registers to the data bus, and posts to ui bus
    public GetWatchesUsecaseController(WatchDataSource dataSource, Bus uiBus) {
        if (dataSource == null)
            throw new IllegalArgumentException("MediaDataSource cannot be null");

        if (uiBus == null)
            throw new IllegalArgumentException("Bus cannot be null");
        mDataSource = dataSource;
        mUiBus = uiBus;
        BusProvider.getDataBusInstance().register(this);
    }

    @Subscribe
    @Override
    public void onWatchesReceived(WatchesWrapper response) { // Receives watches from data bus
        sendWatchesToPresenter(response);
    }

    @Override
    public void requestWatches() {
        mDataSource.getWatches();
    }

    @Override
    public void sendWatchesToPresenter(WatchesWrapper response) {
        mUiBus.post(response);
    }

    @Override
    public void unregister() {
        BusProvider.getDataBusInstance().unregister(this);
    }

    @Override
    public void execute() {
        requestWatches();
    }
}
