package com.doronzehavi.dapper.views.custom_views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.common.utils.Utils;
import com.doronzehavi.dapper.model.data.WatchFileDataSource;
import com.doronzehavi.dapper.model.entities.Watch;

import java.util.Observable;
import java.util.Observer;

/**
 * Draws a WatchView
 */
public class WatchView extends View implements Observer{

    private Watch mWatch;
    private final WatchFileDataSource mDataSource;

    private Bitmap mBackgroundScaledBitmap;
    private Bitmap mBackgroundBitmap;

    public WatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDataSource = WatchFileDataSource.getInstance();
    }

    /**
     * Called when a WatchViewFragment is created.
     * @param watch the watch the fragment wants drawn
     */
    public void setWatch(Watch watch) {
        mWatch = watch;
        invalidate();
        Log.d(Constants.TAG, "Adding this WatchView as an observer.");
        mWatch.addObserver(this);
    }

    /**
     * Backgrounds loaded only when the WatchView has a new watch set.
     */
    private void loadBackground(){
        mBackgroundBitmap = Constants.getBackgroundBitmap(mWatch.getBackgroundKey());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        loadBackground();
        if (mWatch != null && mBackgroundBitmap != null){
            Log.d(Constants.TAG, "Drawing watch!");
            drawWatch(canvas, canvas.getWidth(), canvas.getHeight());
        }
        else {
            Log.e(Constants.TAG, "Either mWatch or mBackgroundBitmap are null in onDraw!");
        }
    }

    private void drawWatch(Canvas canvas, int width, int height) {
        mBackgroundScaledBitmap = Bitmap.createScaledBitmap(mBackgroundBitmap,
                    width, height, true);
        canvas.drawBitmap(Utils.getCircularBitmap(mBackgroundScaledBitmap), 0, 0, null);
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.d(Constants.TAG, "WatchView calling invalidate()");
        invalidate();
    }

}
