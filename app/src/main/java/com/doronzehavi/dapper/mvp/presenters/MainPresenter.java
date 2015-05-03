package com.doronzehavi.dapper.mvp.presenters;

import android.util.Log;

import com.doronzehavi.dapper.common.utils.BusProvider;
import com.doronzehavi.dapper.domain.GetWatchesUsecaseController;
import com.doronzehavi.dapper.model.data.WatchFileDataSource;
import com.doronzehavi.dapper.model.entities.WatchesWrapper;
import com.doronzehavi.dapper.mvp.views.MainView;
import com.squareup.otto.Subscribe;

/**
 * The presenter that manages the UI logic for the main activity.
 */
public class MainPresenter extends Presenter{

    private final MainView mMainView;
    private GetWatchesUsecaseController mGetWatches;

    // Constructor for the presenter
    public MainPresenter(MainView mainView){
        mMainView = mainView;
        /**
         * 1) Loads up it's Usecase that fetches the user's watches.
         */
        mGetWatches = new GetWatchesUsecaseController(
                WatchFileDataSource.getInstance(), BusProvider.getUIBusInstance());
    }

    /**
     * 2) Executes the Usecase and registers for events to listen for watches received.
     */
    @Override
    public void start() {
        Log.d("Dapper", "Presenter started.");
        BusProvider.getUIBusInstance().register(this);
        mMainView.showLoading();
        mGetWatches.execute();
    }

    /**
     * 3) Watches are received from the UI bus. The presenter now displays them on the view.
     */
    @Subscribe
    public void onWatchesReceived(WatchesWrapper response) { // receives watches from ui bus
        Log.d("Dapper", "Presenter received watches.");
        mMainView.showWatches(response);
        mMainView.hideLoading();
    }

    @Override
    public void stop() {
        BusProvider.getUIBusInstance().unregister(this);
    }
}
