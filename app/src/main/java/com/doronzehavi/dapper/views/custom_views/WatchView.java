package com.doronzehavi.dapper.views.custom_views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.doronzehavi.dapper.common.utils.Utils;
import com.doronzehavi.dapper.model.data.WatchFileDataSource;
import com.doronzehavi.dapper.model.entities.Watch;

/**
 * Draws a WatchView
 */
public class WatchView extends View {

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
     * @param mWatch the watch the fragment wants drawn
     */
    public void setWatch(Watch mWatch) {
        this.mWatch = mWatch;
        loadBackground();
        invalidate();
    }

    private void loadBackground(){
        mBackgroundBitmap = mDataSource.getBackgroundBitmap(mWatch.getBackgroundKey());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mWatch != null && mBackgroundBitmap != null){
            drawWatch(canvas, canvas.getWidth(), canvas.getHeight());
        }
    }

    private void drawWatch(Canvas canvas, int width, int height) {
        if (mBackgroundScaledBitmap == null
                || mBackgroundScaledBitmap.getWidth()  != width
                || mBackgroundScaledBitmap.getHeight() != height) {
            mBackgroundScaledBitmap = Bitmap.createScaledBitmap(mBackgroundBitmap,
                    width, height, true /* filter */);
        }
        canvas.drawBitmap(Utils.getCircularBitmap(mBackgroundScaledBitmap), 0, 0, null);
    }
}
