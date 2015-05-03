package com.doronzehavi.dapper.mvp.presenters;

import com.doronzehavi.dapper.mvp.views.ConfigView;


public class ConfigFragmentPresenter extends Presenter {

    private final ConfigView mConfigView;

    public ConfigFragmentPresenter(ConfigView configView) {
        mConfigView = configView;
    }


    @Override
    public void start() {
        mConfigView.loadConfig();
    }

    @Override
    public void stop() {

    }
}
