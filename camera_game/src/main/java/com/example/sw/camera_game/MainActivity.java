package com.example.sw.camera_game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements yulianView.OnColorListener {

    private yulianView yulianView;
    private CircleCrossView circleCrossView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleCrossView = (CircleCrossView) findViewById(R.id.CCV);
        yulianView = (yulianView) findViewById(R.id.YV);
        yulianView.setOnColorListener(this);
        //circleCrossView.setColor(Color.YELLOW);
        // circleCrossView.refresh();

    }

    @Override
    public void onColor(int color) {
        circleCrossView.setColor(color);
        circleCrossView.refresh();
    }
}
