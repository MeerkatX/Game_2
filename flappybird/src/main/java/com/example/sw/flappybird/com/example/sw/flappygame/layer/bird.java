package com.example.sw.flappybird.com.example.sw.flappygame.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;

import com.example.sw.flappybird.com.example.sw.flappygame.GameSurfaceView;

/**
 * bird
 * Created by sw
 * time 2016/1/24  10:01
 */
public class bird extends Father {


    private float Bx, By;
    private float r, speed, acc;
    private Surface surface;
    private Resources res;

    public bird(GameSurfaceView surfaceView) {
        super(surfaceView);
        Bx = sWidth / 2;
        By = sHight / 2;
        r = 60;
        speed = 15;
        acc = 4;
        ;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        canvas.drawCircle(Bx, By, r, paint);

    }

    @Override
    public void logic() {
        By += speed;
        speed += acc;
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        speed -= 20;
    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }
}
