package com.project.rptang.android.switch_button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Stiven on 2017/1/4.
 */

public class SwitchButton extends View {

    private Paint thumbPaint, trackPaint;
    private int width, height;
    private float thumbX, thumbY;
    private Drawable onThumbDrawable, offThumbDrawable, onTrackDrawable, offTrackDrawable;
    private boolean isClosed = true;
    private boolean isMoving = false;
    private boolean isEnable = true;
    private boolean isClicked = false;
    private float downX, movingX;
    private OnStateChangeListener onStateChangeListener;

    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {

        if (thumbPaint == null) {
            thumbPaint = new Paint();
        }
        if (trackPaint == null) {
            trackPaint = new Paint();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (onTrackDrawable != null) {
            width = onTrackDrawable.getIntrinsicWidth();
            height = onTrackDrawable.getIntrinsicHeight();
        }
        if (offTrackDrawable != null) {
            width = Math.max(width, offTrackDrawable.getIntrinsicWidth());
            height = Math.max(height, offTrackDrawable.getIntrinsicHeight());
        }
        setMeasuredDimension(MeasureSpec.getSize(width), MeasureSpec.getSize(height));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnable) return true;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isClicked = true;
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                movingX = event.getX();
                if (!isMoving) {
                    if (downX > thumbX && downX < thumbX + onThumbDrawable.getIntrinsicWidth()) {
                        isMoving = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isClicked = false;
                if(isMoving){
                    if(thumbX >= (width - onThumbDrawable.getIntrinsicWidth()) / 2){
                        isClosed = false;
                    }else {
                        isClosed = true;
                    }
                }else {
                    if(isClosed){
                        isClosed = false;
                    }else {
                        isClosed = true;
                    }
                }
                if(onStateChangeListener != null){
                    onStateChangeListener.stateChange(!isClosed);
                }
                isMoving = false;
                trackPaint.setAlpha(255);
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTrack(canvas);
        drawThumb(canvas);
    }

    private void drawTrack(Canvas canvas) {
        if(isMoving){
            thumbX = thumbX + movingX - downX;
            if(thumbX <= 2 * onThumbDrawable.getIntrinsicWidth() / width){
                thumbX = 2 * onThumbDrawable.getIntrinsicWidth() / width;
                trackPaint.setAlpha(255);
                canvas.drawBitmap(((BitmapDrawable) offTrackDrawable).getBitmap(), 0, 0, trackPaint);
            }else if(thumbX >= width - onThumbDrawable.getIntrinsicWidth() - 2 * onThumbDrawable.getIntrinsicWidth() / width){
                thumbX = width - onThumbDrawable.getIntrinsicWidth() - 2 * onThumbDrawable.getIntrinsicWidth() / width;
                trackPaint.setAlpha(255);
                canvas.drawBitmap(((BitmapDrawable) onTrackDrawable).getBitmap(), 0, 0, trackPaint);
            }else if(thumbX >= (width - onThumbDrawable.getIntrinsicWidth()) / 2){
                trackPaint.setAlpha((int) (thumbX - width / 2 + onThumbDrawable.getIntrinsicWidth() / 2) * 200 / (width - onThumbDrawable.getIntrinsicWidth()) * 2);
                canvas.drawBitmap(((BitmapDrawable) onTrackDrawable).getBitmap(), 0, 0, trackPaint);
            }else {
                trackPaint.setAlpha(255 - (int) thumbX * 200 / (width - offThumbDrawable.getIntrinsicWidth()) * 2);
                canvas.drawBitmap(((BitmapDrawable) offTrackDrawable).getBitmap(), 0, 0, trackPaint);
            }
        }else {
            if (isClosed) {
                canvas.drawBitmap(((BitmapDrawable) offTrackDrawable).getBitmap(), 0, 0, trackPaint);
            } else {
                canvas.drawBitmap(((BitmapDrawable) onTrackDrawable).getBitmap(), 0, 0, trackPaint);
            }
        }

    }

    private void drawThumb(Canvas canvas) {
        if (isMoving) {
            canvas.drawBitmap(((BitmapDrawable) onThumbDrawable).getBitmap(), thumbX, thumbY, thumbPaint);
            downX = movingX;
        } else {
            if (isClosed) {
                if (isClicked) {
                    thumbX = 2 * onThumbDrawable.getIntrinsicWidth() / width;
                    thumbY = (height - onThumbDrawable.getIntrinsicHeight()) / 2;
                    canvas.drawBitmap(((BitmapDrawable) onThumbDrawable).getBitmap(), thumbX, thumbY, thumbPaint);
                } else {
                    thumbX = 2 * offThumbDrawable.getIntrinsicWidth() / width;
                    thumbY = (height - offThumbDrawable.getIntrinsicHeight()) / 2;
                    canvas.drawBitmap(((BitmapDrawable) offThumbDrawable).getBitmap(), thumbX, thumbY, thumbPaint);
                }
            } else {
                thumbX = width - onThumbDrawable.getIntrinsicWidth() - 2 * onThumbDrawable.getIntrinsicWidth() / width;
                thumbY = (height - onThumbDrawable.getIntrinsicHeight()) / 2;
                canvas.drawBitmap(((BitmapDrawable) onThumbDrawable).getBitmap(), thumbX, thumbY, thumbPaint);
            }
        }
    }

    public void setThumbDrawable(Drawable onThumbDrawable, Drawable offThumbDrawable) {
        this.onThumbDrawable = onThumbDrawable;
        this.offThumbDrawable = offThumbDrawable;
        postInvalidate();
    }

    public void setTrackDrawable(Drawable onTrackDrawable, Drawable offTrackDrawable) {
        this.onTrackDrawable = onTrackDrawable;
        this.offTrackDrawable = offTrackDrawable;
        postInvalidate();
    }

    public void setEnable(boolean enable) {
        this.isEnable = enable;
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener){
        this.onStateChangeListener = onStateChangeListener;
    }

    public interface OnStateChangeListener{
        void stateChange(boolean state);
    }
}
