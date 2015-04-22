package com.doronzehavi.model.entities;

import java.io.Serializable;
import java.util.List;


public class WatchesWrapper implements Serializable {

    private List<Watch> watches;

    public WatchesWrapper(List<Watch> watches){
        this.watches = watches;
    }

    public List<Watch> getWatches(){
        return watches;
    }

}
