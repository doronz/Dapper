package com.doronzehavi.dapper.model.entities;

import java.io.Serializable;

/**
 * Describes a Watch
 */
public class Watch implements Serializable {

    // Instance variables
    private String backgroundKey;

    // Constructor
    public Watch(String backgroundKey){
        this.backgroundKey = backgroundKey;
    }

    // Getters
    public String getBackgroundKey() {
        return backgroundKey;
    }
}
