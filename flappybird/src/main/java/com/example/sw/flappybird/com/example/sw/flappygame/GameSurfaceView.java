package com.example.sw.flappybird.com.example.sw.flappygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.sw.flappybird.Constants.Constants;
import com.example.sw.flappybird.com.example.sw.flappygame.layer.Start;
import com.example.sw.flappybird.com.example.sw.flappygame.layer.bird;

/**
 * GameSurfaceView
 * Created by sw
 * time 2016/1/24  9:30
 */
public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder;
    private Paint paint;
    private Thread thread;
    private boolean flag;
    private Canvas canvas;
    private int gameState;//游戏状态
    private bird bird;
    private Start start;

    //声明对象
    public GameSurfaceView(Context context) {
        super(context);
        init();
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        holder = getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);
        setZOrderOnTop(true);
        setKeepScreenOn(true);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    private void initGame() {
        gameState = Constants.GAME_START;//游戏开始
        bird = new bird(this);
        start = new Start(this);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initGame();
        flag = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;

    }

    //绘制
    private void myDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);//清屏
        switch (gameState) {
            case Constants.GAME_START:
                bird.draw(canvas, paint);
                start.draw(canvas, paint);
                break;
            case Constants.GAMEING:

                break;
            case Constants.GAME_END:

                break;
            default:
                break;
        }
    }

    //逻辑
    private void logic() {
        switch (gameState) {
            case Constants.GAME_START:

                break;
            case Constants.GAMEING:
                bird.logic();
                break;
            case Constants.GAME_END:

                break;
            default:
                break;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        start.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    public void setGameState(int i) {
        this.gameState = i;
    }

    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();
            canvas = holder.lockCanvas();
            if (null != canvas) {
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
            logic();
            long end = System.currentTimeMillis();
            if (end - start < 50) {
                try {
                    Thread.sleep(50 - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
