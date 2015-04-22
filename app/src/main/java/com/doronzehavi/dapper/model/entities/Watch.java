package com.doronzehavi.dapper.model.entities;

import java.io.Serializable;

/**
 * Describes a Watch
 */
public class Watch implements Serializable {

    private String backgroundKey;

    public Watch(String backgroundKey){
        this.backgroundKey = backgroundKey;
    }

    public String getBackgroundKey() {
        return backgroundKey;
    }
}
