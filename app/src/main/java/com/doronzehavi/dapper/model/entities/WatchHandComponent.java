package com.doronzehavi.dapper.model.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.format.Time;

import com.doronzehavi.dapper.common.utils.WatchDetails;
import com.doronzehavi.dapper.model.WatchComponent;


/**
 * This represents the hour, minute and second hands as a group.
 */
public class WatchHandComponent extends WatchComponent {
    private final String mWatchHandKey;
    private Time time = new Time();
    Paint mSecondPaint, mMinutePaint, mHourPaint;

    public WatchHandComponent(String watchHandKey){
        this.mWatchHandKey = watchHandKey;
        if (mWatchHandKey.equals(WatchDetails.KEY_WATCHHAND_STANDARD)){
            initStandard();
        }
    }

    private void initStandard(){
        mHourPaint = new Paint();
        mHourPaint.setColor(Color.BLUE);
        mHourPaint.setStrokeWidth(6.f);
        mHourPaint.setAntiAlias(true);
        mHourPaint.setStrokeCap(Paint.Cap.ROUND);

        mMinutePaint = new Paint();
        mMinutePaint.setColor(Color.BLUE);
        mMinutePaint.setStrokeWidth(4.f);
        mMinutePaint.setAntiAlias(true);
        mMinutePaint.setStrokeCap(Paint.Cap.ROUND);

        mSecondPaint = new Paint();
        mSecondPaint.setColor(Color.BLUE);
        mSecondPaint.setStrokeWidth(3.f);
        mSecondPaint.setAntiAlias(true);
        mSecondPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void draw(Canvas canvas, int width, int height) {
        long now = System.currentTimeMillis();
        time.set(now);
        int milliseconds = (int) (now % 1000);

        float centerX = width / 2f;
        float centerY = height / 2f;

        // Tick mark settings
        float tickOffset = width / 10;

        // Time-hands settings
        float secondHandLength = tickOffset; // second hand reaches the edge of the ticks
        float minuteHandLength = tickOffset * 1.5f;
        float hourHandLength = tickOffset * 2.5f;

        float seconds = time.second + milliseconds / 1000f;
        float secRot = seconds / 30f * (float) Math.PI;
        int minutes = time.minute;
        float minRot = minutes / 30f * (float) Math.PI;
        float hrRot = ((time.hour + (minutes / 60f)) / 6f) * (float) Math.PI;


        float secLength = centerX - secondHandLength;
        float minLength = centerX - minuteHandLength;
        float hrLength = centerX - hourHandLength;

        float secX = (float) Math.sin(secRot) * secLength;
        float secY = (float) -Math.cos(secRot) * secLength;
        canvas.drawLine(centerX, centerY, centerX + secX, centerY + secY, mSecondPaint);

        float minX = (float) Math.sin(minRot) * minLength;
        float minY = (float) -Math.cos(minRot) * minLength;
        canvas.drawLine(centerX, centerY, centerX + minX, centerY + minY, mMinutePaint);

        float hrX = (float) Math.sin(hrRot) * hrLength;
        float hrY = (float) -Math.cos(hrRot) * hrLength;
        canvas.drawLine(centerX, centerY, centerX + hrX, centerY + hrY, mHourPaint);
    }
}
