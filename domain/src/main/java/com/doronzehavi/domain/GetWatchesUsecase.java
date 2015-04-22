package com.doronzehavi.domain;

import com.doronzehavi.model.entities.WatchesWrapper;

/**
 * Rrepresentation of the usecase that loads up all the watches.
 */
public interface GetWatchesUsecase extends Usecase {

    /**
     * Callback used to be notified when the user's watches have been
     * received
     * @param response the response containing the list of watches
     */
    public void onWatchesReceived(WatchesWrapper response);

    /**
     * Request from our datasource the user's watches
     */
    public void requestWatches();

    /**
     * Sends the watches to the presenter
     * @param response
     */
    public void sendWatchesToPresenter(WatchesWrapper response);

    public void unregister();


}
