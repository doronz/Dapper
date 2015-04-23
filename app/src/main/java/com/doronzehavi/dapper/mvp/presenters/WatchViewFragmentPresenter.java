package com.doronzehavi.dapper.mvp.presenters;

import com.doronzehavi.dapper.mvp.views.WatchFaceView;


public class WatchViewFragmentPresenter extends Presenter {

    private final WatchFaceView mWatchFaceView;

    public WatchViewFragmentPresenter(WatchFaceView mWatchFaceView) {
        this.mWatchFaceView = mWatchFaceView;

    }

    @Override
    public void start() {
        mWatchFaceView.updateWatch();
    }

    @Override
    public void stop() {

    }
}
