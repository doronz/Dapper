package com.doronzehavi.dapper.mvp.views;

import com.doronzehavi.dapper.model.entities.Watch;
import com.doronzehavi.dapper.model.entities.WatchesWrapper;

import java.util.List;

/**
 * This represents the main view of the app which shows watches to select from and a configuration fragment.
 */

public interface MainView {
    void showWatches(WatchesWrapper watches);

    void showLoading();

    void hideLoading();

    void updateMainFrag(int position);

    List<Watch> getWatches();

    void saveWatches();
}
