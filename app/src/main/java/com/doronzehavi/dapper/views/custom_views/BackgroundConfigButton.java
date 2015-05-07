package com.doronzehavi.dapper.views.custom_views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.doronzehavi.dapper.Dapper;
import com.doronzehavi.dapper.R;
import com.doronzehavi.dapper.common.utils.Utils;

/**
 * Created by D on 5/2/2015.
 */
public class BackgroundConfigButton extends ConfigButton {
    public BackgroundConfigButton(Context context) {
        super(context);
    }
    int width = (int) getResources().getDimension(R.dimen.imagebutton_width);
    int height = (int) getResources().getDimension(R.dimen.imagebutton_height);
    public void setImage(String key) {
        setImageDrawable(getBackgroundDrawable(key, width, height));
    }

    private Drawable getBackgroundDrawable(String key, int width, int height){
        return new BitmapDrawable(Dapper.getContext().getResources(),
                Utils.getCircularBitmap(Bitmap.createScaledBitmap(Utils.getBackgroundBitmap(key),
                        width, height, true)));
    }
}
