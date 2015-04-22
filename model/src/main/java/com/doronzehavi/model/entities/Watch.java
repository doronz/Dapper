package com.doronzehavi.model.entities;

import java.io.Serializable;

/**
 * Created by D on 4/21/2015.
 */
public class Watch implements Serializable {

    private String id;
    private String background;

    public Watch(String id, String background){
        this.id = id;
        this.background = background;
    }

    public String getId() {
        return id;
    }

    public String getBackground() {
        return background;
    }
}
