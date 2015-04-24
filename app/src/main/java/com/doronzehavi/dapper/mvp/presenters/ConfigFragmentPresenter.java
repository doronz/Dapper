package com.doronzehavi.dapper.mvp.presenters;

import com.doronzehavi.dapper.mvp.views.ConfigView;


public class ConfigFragmentPresenter extends Presenter {

    private final ConfigView mConfigView;

    public ConfigFragmentPresenter(ConfigView configView) {
        mConfigView = configView;
       //mLoadConfig = new LoadConfigUsecaseController(WatchFileDataSource.getInstance(), BusProvider.getUIBusInstance());
        //BusProvider.getUIBusInstance().register(this);
    }

/*    @Subscribe
    public void onConfigDataReady(WatchFileDataSource dataSource){
        Log.d(Constants.TAG, "ConfigFragmentPresenter: watch data received");
        mConfigView.loadConfig(dataSource);
    }*/

    @Override
    public void start() {
        mConfigView.loadConfig();
        //mLoadConfig.execute();
    }

    @Override
    public void stop() {
        //BusProvider.getUIBusInstance().unregister(this);
    }
}
