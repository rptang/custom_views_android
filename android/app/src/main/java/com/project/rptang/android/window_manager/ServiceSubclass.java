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
import android.view.Window;
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
 *
 * 在service中启动一个全局的dialog
 */


public class ServiceSubclass extends Service {

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private DisplayMetrics displayMetrics;
    private TextView textView;
    private View view;

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

        view = LayoutInflater.from(this).inflate(R.layout.layout_window, null);
        windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

//        layoutParams = new WindowManager.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                PixelFormat.TRANSLUCENT);
//        layoutParams = new WindowManager.LayoutParams();
//        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//        layoutParams.format = PixelFormat.RGBA_8888;
//        layoutParams.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//
//
//        layoutParams.gravity = Gravity.TOP;
//        windowManager.addView(view, layoutParams);

        /*AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();*/

        createDialog();

        return super.onStartCommand(intent, flags, startId);
    }

    private void createDialog(){
        Dialog dialog = new Dialog(getApplicationContext(),R.style.DialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.TOP);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        dialogWindow.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.show();

    }

    @Override
    public void onDestroy() {
        if(view.getParent() != null){
            windowManager.removeView(view);
        }
        super.onDestroy();
    }
}
