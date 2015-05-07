package com.doronzehavi.dapper.views.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.doronzehavi.dapper.common.utils.WatchDetails;
import com.doronzehavi.dapper.model.entities.BackgroundComponent;
import com.doronzehavi.dapper.model.entities.Watch;
import com.doronzehavi.dapper.model.entities.WatchHandComponent;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Draws a WatchView
 */
public class WatchView extends View implements Observer{

    private Watch mWatch;

    private Timer mTimer;


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
        mWatch.addObserver(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWatch(canvas, canvas.getWidth(), canvas.getHeight());
    }

    private void drawWatch(Canvas canvas, int width, int height) {
        drawBackground(canvas, width, height);
        drawWatchHands(canvas, width, height);
    }


    @Override
    public void update(Observable observable, Object data) {
        invalidate();
    }

    // TODO: Draw the background once. Only draw again if it is a different background.
    private void drawBackground(Canvas canvas, int width, int height){
        BackgroundComponent background = WatchDetails.MAP_KEY_TO_BG_COMP.get(mWatch.getBackgroundKey());
        background.draw(canvas, width, height);
    }

    private void drawWatchHands(Canvas canvas, int width, int height) {
        WatchHandComponent watchHands = WatchDetails.MAP_KEY_TO_WATCHHAND_COMP.get(mWatch.getWatchHandKey());
        watchHands.draw(canvas, width, height);
    }

    protected Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            update();
        }
    };

    /**
     * Updates the watch periodically such that the second hand either ticks or sweeps.
     * @param sweep set to true if you want a smooth second hand
     */
    private void startTicking(boolean sweep){
        mTimer = new Timer();

        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0); // tick watch
            }
        };
        long now = (long) Math.ceil((System.currentTimeMillis() / 1000) + 1) * 1000; // round to next nearest second
        Date date = new Date();
        date.setTime(now);
        if (sweep) {
            mTimer.schedule(mTimerTask, 0, 33); // update at 30 fps
        }else {
            mTimer.schedule(mTimerTask, date, 1000);
        }

    }

    private void stopTicking(){
        if (mTimer != null)
            mTimer.cancel();
    }



    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startTicking(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopTicking();
    }

    private void update(){
        invalidate();
    }
}
