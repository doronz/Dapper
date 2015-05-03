package com.doronzehavi.dapper.common.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.doronzehavi.dapper.Dapper;

/**
 * Utility methods
 */
public class Utils {

    /**
     * Clips given bitmap to circle
     * @param bitmap the square bitmap to clip
     * @return a circular bitmap
     */
    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap output;
        if (bitmap.getWidth() > bitmap.getHeight())
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        else
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        float r;
        if (bitmap.getWidth() > bitmap.getHeight())
            r = bitmap.getHeight() / 2;
        else
            r = bitmap.getWidth() / 2;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    public Drawable getBackgroundDrawable(Bitmap bitmap, int width, int height){
        return new BitmapDrawable(Dapper.getContext().getResources(),
                Utils.getCircularBitmap(Bitmap.createScaledBitmap(bitmap,
                        width, height, true)));
    }
}
