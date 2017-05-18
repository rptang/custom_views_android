package com.project.rptang.android.window_manager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.project.rptang.android.R;

/**
 * android
 * com.project.rptang.android.window_manager
 * ServiceSubclass
 * <p>
 * Created by Stiven on 2017/5/18.
 * Copyright © 2017 ZYZS-TECH. All rights reserved.
 */

public class ServiceSubclass extends Service {

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private DisplayMetrics displayMetrics;
    private TextView textView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        View view = LayoutInflater.from(this).inflate(R.layout.layout_window, null);
        windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        layoutParams = new WindowManager.LayoutParams();
//        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//        layoutParams.format = PixelFormat.TRANSLUCENT;
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        layoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);

        layoutParams.gravity = Gravity.TOP;
        windowManager.addView(view, layoutParams);
        /*AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();*/
        /*View view = LayoutInflater.from(this).inflate(R.layout.layout_window, null);

//        layoutParams = new WindowManager.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                PixelFormat.TRANSLUCENT);

//        layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//        layoutParams.format = PixelFormat.TRANSLUCENT;
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//
//        layoutParams.gravity = Gravity.CENTER | Gravity.TOP;
//        windowManager.addView(view, layoutParams);*/

        return super.onStartCommand(intent, flags, startId);
    }
}
