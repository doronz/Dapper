package com.doronzehavi.dapper.domain;

import com.doronzehavi.dapper.common.utils.BusProvider;
import com.doronzehavi.dapper.model.WatchDataSource;
import com.doronzehavi.dapper.model.entities.WatchesWrapper;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Implementation of (@link GetWatchesUsecase}
 */
public class GetWatchesUsecaseController implements GetWatchesUsecase {
    private final Bus mUiBus;
    private final WatchDataSource mDataSource;


    public GetWatchesUsecaseController(WatchDataSource dataSource, Bus uiBus) {
        if (dataSource == null)
            throw new IllegalArgumentException("MediaDataSource cannot be null");

        if (uiBus == null)
            throw new IllegalArgumentException("Bus cannot be null");
        mDataSource = dataSource;
        mUiBus = uiBus;
        /**
         *  1) Registers to the data bus here.
         */
        BusProvider.getDataBusInstance().register(this);
    }

    /**
     * 2) Receives watches from data bus here.
    */
    @Subscribe
    @Override
    public void onWatchesReceived(WatchesWrapper response) {
        sendWatchesToPresenter(response); // Then sends them via the UI bus to the presenter
    }

    @Override
    public void requestWatches() {
        mDataSource.getWatches();
    }


    /**
     * 3) Forwards them to the presenter via the UI bus here.
     */
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
