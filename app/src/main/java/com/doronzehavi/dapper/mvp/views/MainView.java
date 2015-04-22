package com.doronzehavi.dapper.mvp.views;

import com.doronzehavi.model.entities.WatchesWrapper;

/**
 * This represents the main view of the app which shows watches to select from and a configuration fragment.
 */

public interface MainView {
    void showWatches(WatchesWrapper watches);

    void showLoading();

    void hideLoading();

}
