package com.project.rptang.android.window_manager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.project.rptang.android.R;

public class WindowManagerActivity extends Activity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_manager);

        findViewById(R.id.tvTitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WindowManagerActivity.this,StatusBarActivity.class));
                StatusBarCompat.compat(WindowManagerActivity.this, Color.parseColor("#4CDA64"));
            }
        });


//        init();

    }

    @Override
    protected void onResume() {
        StatusBarCompat.compat(WindowManagerActivity.this, getResources().getColor(R.color.colorAccent));
        super.onResume();
    }

    private void init(){
        intent = new Intent();
        intent.setAction("cc.test.com");
        intent.setPackage("com.project.rptang.android");
        startService(intent);
    }
}
