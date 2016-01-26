package com.example.sw.camera_game;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by sw
 * time 2016/1/23
 */
public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback {
    public final static String TAG = "CS";
    protected Camera camera;
    private SurfaceHolder holder;
    private boolean ispreview;

    //普通调用
    public CameraSurface(Context context) {
        super(context);
        init();
    }

    //得到布局属性
    public CameraSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        holder = getHolder();
        holder.addCallback(this);
        ispreview = false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();//默认后置
        if (null != camera) {
            try {
                camera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
                camera.release();
                camera = null;
            }
        }
        //暂不可用
        //Camera.Parameters parameters = camera.getParameters();
        //parameters.setPreviewSize(getWidth(), getHeight()); //设置camera预览的尺寸
        //camera.setParameters(parameters);
        camera.startPreview();
        ispreview = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //自动对焦
        camera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    camera.cancelAutoFocus();
                }
            }
        });
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (null != camera) {
            if (ispreview) {
                camera.stopPreview();
            }
            camera.release(); //释放camera
            camera = null;
        }

    }
}
