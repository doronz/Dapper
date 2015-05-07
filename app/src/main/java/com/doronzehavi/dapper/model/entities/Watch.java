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

    // Constructor
    public Watch(){

    }
    public Watch(int position){
        this.mPosition = position;
        Log.d(Constants.TAG, "Watch #" + this.mPosition + " created.");
    }

    public Watch(int position, String backgroundKey){
        this.mPosition = position;
        this.mBackgroundKey = backgroundKey;
    }


    public void update(String componentKey, String componentResourceKey){
        if (componentKey.equals(Constants.KEY_BACKGROUND)){
            this.mBackgroundKey = componentResourceKey;
            Log.d(Constants.TAG, "Watch updated with new background.");
        }
        setChanged();
        notifyObservers();
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

}
