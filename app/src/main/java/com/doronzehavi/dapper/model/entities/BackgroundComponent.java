package com.doronzehavi.dapper.model.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.doronzehavi.dapper.common.utils.Utils;
import com.doronzehavi.dapper.model.WatchComponent;

import java.io.Serializable;

/**
 * Background component
 */
public class BackgroundComponent extends WatchComponent implements Serializable {
    public BackgroundComponent(String backgroundKey){
        key = backgroundKey;
    }

    public void draw(Canvas canvas, int width, int height) {
        Bitmap backgroundScaledBitmap = Bitmap.createScaledBitmap(Utils.getBackgroundBitmap(key),
                width, height, true);
        canvas.drawBitmap(Utils.getCircularBitmap(backgroundScaledBitmap), 0, 0, null);
    }
}
