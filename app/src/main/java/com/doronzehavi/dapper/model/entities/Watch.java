package com.doronzehavi.dapper.model.entities;

import android.util.Log;

import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.model.WatchComponent;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Maintains the state of a particular watch.
 * Also maintains the possible component options for this watch.
 */
public class Watch implements Serializable {

    // Instance variables
    private final int mPosition;
    private String mBackgroundKey;

    // All available components for this watch.
    private List<List<WatchComponent>> mComponents;
    // All available backgrounds for this watch.
    private Map<String, WatchComponent> mBackgroundComponents;

    // Constructor
    public Watch(int position){
        this.mPosition = position;
        init();


        Log.d(Constants.TAG, "Watch #" + this.mPosition + " created.");
    }

    private void init(){
        switch(mPosition) {
            case 0: mBackgroundComponents = Constants.WATCH_0_BACKGROUND_COMPONENTS;
                    break;
            case 1: mBackgroundComponents = Constants.WATCH_1_BACKGROUND_COMPONENTS;
                    break;
        }
    }

    // Getters
    public String getBackgroundKey() {
        if (mBackgroundKey == null) {
            switch(mPosition) {
                case 0: mBackgroundKey = Constants.WATCH_0_DEFAULT_BACKGROUND_KEY;
                        break;
                case 1: mBackgroundKey = Constants.WATCH_1_DEFAULT_BACKGROUND_KEY;
            }
        }
        return mBackgroundKey;
    }
    public int getPosition() {
        return mPosition;
    }

    public Map<String, WatchComponent> getBackgroundComponents() {
        return mBackgroundComponents;
    }


}
