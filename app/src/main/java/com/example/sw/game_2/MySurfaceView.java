package com.example.sw.game_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * MySurfaceView
 *
 * @author:
 * @time: 2016-01-22 10:17
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    public final static String TAG = "MySurfaceView";

    private SurfaceHolder holder;

    private Canvas canvas;
    private Paint paint;

    private Thread thread;
    private boolean flag;

    private float x, y;
    private float speedX, speedY;
    private float radius;
    private int color;

    private Vector local;
    private Vector acc;
    private Vector speed;

    private float rectX, rectY, rectWidth, rectHeight;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        holder = getHolder();
        holder.addCallback(this);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

    }

    /**
     * 初始化游戏
     */
    private void initGame() {
        x = 0;
        y = 0;
        speedX = 20;
        speedY = 30;
        radius = 50;
        color = Color.RED;
        speed = new Vector(10, 20);
        local = new Vector(100, 100);
        acc = new Vector(1.0f, 2.0f);
        rectX = getWidth() / 2 - 100;
        rectY = getHeight() / 2 - 80;
        rectWidth = 200;
        rectHeight = 200;

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated");

        initGame();

        flag = true;

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "surfaceDestroyed");
        flag = false;
    }

    /**
     * 画图
     *
     * @param canvas
     */
    private void myDraw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(rectX, rectY, rectX + rectWidth, rectY + rectHeight, paint);
        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(local.x, local.y, radius, paint);
    }

    /**
     * 逻辑
     */
    private void logic() {
        x += speedX;
        y += speedY;
        //碰到检测
        if (x >= getWidth() || x < 0 || CtoC(x, y, local.x, local.y)) {
            speedX = -speedX;
        }
        if (y >= getHeight() || y < 0 || CtoC(x, y, local.x, local.y)) {
            speedY = -speedY;
        }
        speed.Limit(20);
        speed.add(acc);
        local.add(speed);
        if (local.x >= getWidth() || local.x < 0 || hit(rectX, rectY, local.x, local.y) || CtoC(x, y, local.x, local.y)) {
            speed.x = -speed.x;
            acc.x = -acc.x;
        }
        if (local.y >= getHeight() || local.y < 0 || hit(rectX, rectY, local.x, local.y) || CtoC(x, y, local.x, local.y)) {
            speed.y = -speed.y;
            acc.y = -acc.y;
        }


    }

    public boolean hit(float rX, float rY, float x, float y) {
        if (x + radius < rX) {
            return false;
        }
        if (x - radius > rX + 200) {
            return false;
        }
        if (y + radius < rY) {
            return false;
        }
        if (y - radius > rY + 200) {
            return false;
        }
        /*if(Math.pow(rX-x,2)+Math.pow(rY-y,2)>Math.pow(radius+radius,2)){
            return false;
        }else if (Math.pow(rX+rectWidth-x,2)+Math.pow(rY+rectHeight-y,2)>Math.pow(radius+radius,2)){
            return false;
        }else if (Math.pow(rX-x,2)+Math.pow(rY-y,2)>Math.pow(radius+radius,2)){
            return false;
        }else if (Math.pow(rX-x,2)+Math.pow(rY+rectHeight-y,2)>Math.pow(radius+radius,2)){
            return false;
        }*/
        return true;
    }

    public boolean CtoC(float x, float y, float x2, float y2) {
        double dis = Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2);
        if (dis < Math.pow(radius + radius, 2))
            return true;
        else
            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int[] colors = new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

        color = colors[new Random().nextInt(colors.length)];

        //     radius = new Random().nextInt(30) + 50;

        Vector touch = new Vector(x, y);
        acc = Vector.sub(touch, local);
        acc.normalize();
        acc.mult(15.0f);
        return super.onTouchEvent(event);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis(); //开始时间

            canvas = holder.lockCanvas(); //加锁
            if (null != canvas) {
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas); //解锁
            }
            logic();

            long end = System.currentTimeMillis(); //结束时间

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

