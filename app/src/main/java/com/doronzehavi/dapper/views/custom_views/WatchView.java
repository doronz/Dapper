package com.doronzehavi.dapper.views.custom_views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.doronzehavi.dapper.common.utils.Constants;
import com.doronzehavi.dapper.model.entities.Watch;

import java.util.Observable;
import java.util.Observer;

/**
 * Draws a WatchView
 */
public class WatchView extends View implements Observer{

    private Watch mWatch;




    private Bitmap mBackgroundBitmap;

    public WatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
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


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWatch(canvas, canvas.getWidth(), canvas.getHeight());
    }

    private void drawWatch(Canvas canvas, int width, int height) {
        drawBackground(canvas, width, height);
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.d(Constants.TAG, "WatchView calling invalidate()");
        invalidate();
    }

    private void drawBackground(Canvas canvas, int width, int height){
        Constants.MAP_KEY_TO_BG_COMP.get(mWatch.getBackgroundKey()).draw(canvas, width, height);
    }

}
