package com.doronzehavi.dapper.domain;

/**
 * Interface of the usecase that loads up the current watch's config.
 */
public interface LoadConfigUsecase extends Usecase {
    public void sendDataToPresenter();
}
