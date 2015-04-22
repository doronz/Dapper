package com.doronzehavi.dapper;

import android.app.Application;
import android.content.Context;

/**
 * Global access to app context.
 */
public class Dapper extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
