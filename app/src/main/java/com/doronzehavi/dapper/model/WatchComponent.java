package com.doronzehavi.dapper.model;

/**
 * Created by D on 4/30/2015.
 */
public abstract class WatchComponent {
    protected String key; // The key that identifies the particular resource for this component
    public String getKey() {
        return key;
    }
}