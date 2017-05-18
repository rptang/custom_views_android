package com.project.rptang.android.window_manager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
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

        init();

    }

    private void init(){
        intent = new Intent();
        intent.setAction("cc.test.com");
        intent.setPackage("com.project.rptang.android");
        startService(intent);
    }
}
