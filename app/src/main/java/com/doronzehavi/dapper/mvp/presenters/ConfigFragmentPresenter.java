package com.doronzehavi.dapper.mvp.presenters;

import android.util.Log;

import com.doronzehavi.dapper.common.utils.BusProvider;
import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.domain.LoadConfigUsecaseController;
import com.doronzehavi.dapper.model.data.WatchFileDataSource;
import com.doronzehavi.dapper.mvp.views.ConfigView;
import com.squareup.otto.Subscribe;


public class ConfigFragmentPresenter extends Presenter {

    private final ConfigView mConfigView;
    private LoadConfigUsecaseController mLoadConfig;

    public ConfigFragmentPresenter(ConfigView configView) {
        mConfigView = configView;
        mLoadConfig = new LoadConfigUsecaseController(WatchFileDataSource.getInstance(), BusProvider.getUIBusInstance());
        BusProvider.getUIBusInstance().register(this);
    }

    @Subscribe
    public void onConfigDataReady(WatchFileDataSource dataSource){
        Log.d(Constants.TAG, "ConfigFragmentPresenter: watch data received");
        mConfigView.loadConfig(dataSource);
    }

    @Override
    public void start() {
        mLoadConfig.execute();
    }

    @Override
    public void stop() {
        BusProvider.getUIBusInstance().unregister(this);
    }
}
