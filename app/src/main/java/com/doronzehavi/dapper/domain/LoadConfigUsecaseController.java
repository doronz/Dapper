package com.doronzehavi.dapper.domain;

import com.doronzehavi.dapper.model.data.WatchFileDataSource;
import com.squareup.otto.Bus;

/**
 * Responsible for loading the configuration options for a watch
 */
public class LoadConfigUsecaseController implements LoadConfigUsecase {

    private final Bus mUiBus;
    private WatchFileDataSource mDataSource;

    public LoadConfigUsecaseController(WatchFileDataSource dataSource, Bus uiBus) {
        if (dataSource == null)
            throw new IllegalArgumentException("MediaDataSource cannot be null");

        if (uiBus == null)
            throw new IllegalArgumentException("Bus cannot be null");
        mUiBus = uiBus;
        mDataSource = dataSource;
    }

    @Override
    public void sendDataToPresenter() {
        mUiBus.post(mDataSource);
    }

    @Override
    public void execute() {
        sendDataToPresenter();
    }


}
