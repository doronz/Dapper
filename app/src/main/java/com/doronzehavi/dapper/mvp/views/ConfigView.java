package com.doronzehavi.dapper.mvp.views;

import com.doronzehavi.dapper.model.WatchDataSource;

/**
 * This represents the config fragment view of the app which shows the currently selected watch's
 * configuration options.
 */
public interface ConfigView {
    void loadConfig(WatchDataSource dataSource);
}
