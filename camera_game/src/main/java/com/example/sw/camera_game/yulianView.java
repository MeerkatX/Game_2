package com.example.sw.camera_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by sw
 * time 2016/1/23
 */
public class yulianView extends CameraSurface implements Camera.PreviewCallback {

    private OnColorListener colorListener;

    public yulianView(Context context) {
        super(context);
    }

    public yulianView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);
        camera.setPreviewCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        super.surfaceChanged(holder, format, width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.setPreviewCallback(null);
        super.surfaceDestroyed(holder);
    }

    //Camera转化为图片
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Camera.Size size = camera.getParameters().getPreviewSize();
        YuvImage image = new YuvImage(data, ImageFormat.NV21, size.width, size.height, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (null != image) {
            image.compressToJpeg(new Rect(0, 0, size.width, size.height), 100, outputStream);
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Bitmap bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(outputStream.toByteArray()));
        int color = bitmap.getPixel(size.width / 2, size.height / 2);
        if (null != colorListener) {
            colorListener.onColor(color);
        }
    }

    public void setOnColorListener(OnColorListener onColorListener) {
        this.colorListener = onColorListener;
    }

    public interface OnColorListener {
        void onColor(int color);
    }

}
