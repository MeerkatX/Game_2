package com.example.sw.camera_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by sw
 * time 2016/1/23
 */
public class CircleCrossView extends SurfaceView implements SurfaceHolder.Callback {

    public final static String TAG = "CCV";
    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    private int color;

    public CircleCrossView(Context context) {
        super(context);
        init();
    }

    public CircleCrossView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        holder = getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);//设置摄像机为背景
        setKeepScreenOn(true);
        setZOrderOnTop(true);//设置摄像机为背景
        paint = new Paint();
        paint.setAntiAlias(true);
        color = Color.RED;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        refresh();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void setColor(int color) {
        this.color = color;
    }

    private void Draw(Canvas canvas) {

        //paint.setColor(Color.WHITE);
        //canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);//设置坐标系为view中心
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(15);
        float radius = getHeight() / 4;
        canvas.drawCircle(0, 0, radius, paint);//画圈
        paint.setStrokeWidth(4);//设置线粗细
        canvas.drawLine(0, paint.getStrokeWidth() / 2, 0, -radius, paint);//画线
        canvas.drawLine(-paint.getStrokeWidth() / 2, 0, radius, 0, paint);
        canvas.restore();
    }

    public void refresh() {
        canvas = holder.lockCanvas();
        if (null != canvas) {
            Draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
        logic();
    }

    private void logic() {

    }

}
