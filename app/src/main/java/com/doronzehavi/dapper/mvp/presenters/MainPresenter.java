package com.doronzehavi.dapper.mvp.presenters;

import android.util.Log;

import com.doronzehavi.common.utils.BusProvider;
import com.doronzehavi.dapper.mvp.views.MainView;
import com.doronzehavi.domain.GetWatchesUsecaseController;
import com.doronzehavi.model.data.WatchFileDataSource;
import com.doronzehavi.model.entities.WatchesWrapper;
import com.squareup.otto.Subscribe;

/**
 * The presenter that manages the UI logic.
 */
public class MainPresenter extends Presenter{

    private final MainView mMainView;
    private GetWatchesUsecaseController mGetWatches;

    // Constructor for the presenter
    public MainPresenter(MainView mainView){
        mMainView = mainView;
        // Loads up it's first task: Getting watches from the model.
        mGetWatches = new GetWatchesUsecaseController(
                WatchFileDataSource.getInstance(), BusProvider.getUIBusInstance());
    }

    @Subscribe
    public void onWatchesReceived(WatchesWrapper response) { // receives watches from ui bus
        Log.d("Dapper", "Presenter received watches.");
        mMainView.showWatches(response);
    }


    @Override
    public void start() {
        Log.d("Dapper", "Presenter started.");
        BusProvider.getUIBusInstance().register(this);
        mGetWatches.execute();
    }

    @Override
    public void stop() {

    }
}
