package com.doronzehavi.dapper.domain;

import com.doronzehavi.dapper.model.data.WatchFileDataSource;
import com.doronzehavi.dapper.model.entities.Watch;

import java.util.List;


public class SaveWatchesUsecaseController implements SaveWatchesUsecase {

    private final WatchFileDataSource mDataSource;
    private final List<Watch> mWatches;

    public SaveWatchesUsecaseController(List<Watch> watches, WatchFileDataSource dataSource){
        this.mDataSource = dataSource;
        this.mWatches = watches;
    }

    @Override
    public void saveWatches() {
        mDataSource.saveWatches(mWatches);
    }

    @Override
    public void execute() {
        saveWatches();
    }
}
