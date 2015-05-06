package com.doronzehavi.dapper.model.entities;

import com.doronzehavi.dapper.model.WatchComponent;

import java.io.Serializable;

/**
 * Background component
 */
public class BackgroundComponent extends WatchComponent implements Serializable {
    public BackgroundComponent(String backgroundKey){
        key = backgroundKey;
    }
}
