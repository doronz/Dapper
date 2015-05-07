package com.doronzehavi.dapper.model.entities;

import android.util.Log;

import com.doronzehavi.dapper.common.utils.Constants;

import java.io.Serializable;
import java.util.Observable;

/**
 * Maintains the state of a particular watch.
 * Also maintains the possible component options for this watch.
 */
public class Watch extends Observable implements Serializable  {

    // Instance variables
    private int mPosition;
    private String mBackgroundKey;
    private String mWatchHandKey;

    // Constructor


    public Watch(int position, String backgroundKey, String watchHandKey){
        this.mPosition = position;
        this.mBackgroundKey = backgroundKey;
        this.mWatchHandKey = watchHandKey;
    }


    public void update(String componentKey, String componentResourceKey){
        if (componentKey.equals(Constants.KEY_BACKGROUND)){
            this.mBackgroundKey = componentResourceKey;
            setChanged();
            notifyObservers(Constants.KEY_BACKGROUND);
            Log.d(Constants.TAG, "Watch updated with new background.");
        }
        else if (componentKey.equals(Constants.KEY_WATCHHAND)){
            this.mWatchHandKey = componentResourceKey;
            setChanged();
            notifyObservers(Constants.KEY_WATCHHAND);
            Log.d(Constants.TAG, "Watch updated with new watch hands.");
        }

    }






    // Getters
    public String getBackgroundKey() {
        return mBackgroundKey;
    }
    public int getPosition() {
        return mPosition;
    }

    public String getWatchHandKey() {
        return mWatchHandKey;
    }
}
