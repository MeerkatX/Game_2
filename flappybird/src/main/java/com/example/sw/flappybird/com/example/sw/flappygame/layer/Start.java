package com.example.sw.flappybird.com.example.sw.flappygame.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.sw.flappybird.Constants.Constants;
import com.example.sw.flappybird.com.example.sw.flappygame.GameSurfaceView;

/**
 * Start
 * Created by sw
 * time 2016/1/24  10:02
 */
public class Start extends Father {
    private float x, y;
    private float w, h;
    private float shigh, swidth;

    public Start(GameSurfaceView surfaceView) {
        super(surfaceView);
        w = 200;
        h = 100;
        x = sWidth / 2 - w / 2;
        y = sHight / 2 + 400 - h / 2;
        shigh = 20;
        swidth = 50;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(x, y, x + w, y + h, paint);
        paint.setColor(Color.BLACK);
        Path path = new Path();
        path.moveTo(x + w / 2 - shigh / 2, y + h / 2 - swidth / 2);
        path.lineTo(x + w / 2 - shigh / 2, y + h / 2 + swidth);
        path.lineTo(x + w / 2 + shigh, y + h / 2 + swidth / 2);
        canvas.drawPath(path, paint);
    }

    public void logic() {
    }

    public void onTouchEvent(MotionEvent event) {
        int touchx = (int) event.getX();
        int touchy = (int) event.getY();
        if (touchx > x && touchx < x + w && touchy > y && touchy < y + h) {
            surfaceView.setGameState(Constants.GAMEING);
        }
    }

    public void onKeyDown(int keyCode, KeyEvent event) {

    }

}
