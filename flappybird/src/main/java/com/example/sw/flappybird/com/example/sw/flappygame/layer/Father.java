package com.example.sw.flappybird.com.example.sw.flappygame.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.sw.flappybird.com.example.sw.flappygame.GameSurfaceView;

/**
 * Father
 * Created by sw
 * time 2016/1/24  10:13
 */
public abstract class Father {
    protected GameSurfaceView surfaceView;
    protected int sWidth, sHight;//屏幕宽高
    protected Resources res;

    public Father(GameSurfaceView surfaceView) {
        this.surfaceView = surfaceView;
        this.sWidth = surfaceView.getWidth();
        this.sHight = surfaceView.getHeight();
    }

    public abstract void draw(Canvas canvas, Paint paint);

    public abstract void logic();

    public abstract void onTouchEvent(MotionEvent event);

    public abstract void onKeyDown(int keyCode, KeyEvent event);
}
