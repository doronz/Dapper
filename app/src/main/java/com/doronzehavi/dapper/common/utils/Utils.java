package com.doronzehavi.dapper.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.doronzehavi.dapper.Dapper;
import com.doronzehavi.dapper.model.entities.Watch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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


    public static Drawable getBackgroundDrawable(Bitmap bitmap, int width, int height){
        return new BitmapDrawable(Dapper.getContext().getResources(),
                Utils.getCircularBitmap(Bitmap.createScaledBitmap(bitmap,
                        width, height, true)));
    }

     public static void saveWatchesToFile(List<Watch> watches){
         try {
             FileOutputStream fos = Dapper.getContext().openFileOutput(Constants.USER_DATA_PATH, Context.MODE_PRIVATE);
             ObjectOutputStream os = new ObjectOutputStream(fos);
             os.writeObject(watches);
             fos.close();
             os.close();
             Log.d(Constants.TAG, "Watches saved successfully.");
         } catch (Exception ex) {
             Log.e(Constants.TAG, "Error: Unable to save watches to file! - " + ex.getMessage());
             ex.printStackTrace ();
         }
    }

    public static boolean userFileExists(){
        File userFile = Dapper.getContext().getFileStreamPath(Constants.USER_DATA_PATH);
        boolean exists = userFile.exists();
        Log.d(Constants.TAG, "User file exists: " + exists);
        return exists;
    }

    public static List<Watch> loadWatchesFromFile() throws IOException {
        FileInputStream fis = Dapper.getContext().openFileInput(Constants.USER_DATA_PATH);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Watch> watches = null;
        try {
            watches = (ArrayList<Watch>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ois.close();
        return watches;
    }
}
