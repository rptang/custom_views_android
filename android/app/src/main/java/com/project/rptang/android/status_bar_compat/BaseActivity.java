package com.project.rptang.android.status_bar_compat;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.project.rptang.android.R;

/**
 * 为保持状态栏与app主题色一致，我们需要在应用中设置状态栏的颜色，
 * 由于版本不同，处理的方式也不同
 * 今天我们就来处理版本4.4以及版本5.0的状态栏沉浸
 */

public class BaseActivity extends AppCompatActivity {

    private int mStatusBarColor = Color.parseColor("#FF4500");

    private View mStatusBar;

    /**
     * 当前沉浸模式，默认为布局沉浸式
     */
    private String immersionType = TYPE_LAYOUT;

    /**
     * 仅仅改变状态栏颜色的沉浸模式
     */
    public static final String TYPE_LAYOUT = "type_layout";

    /**
     * 将原布局背景扩散至状态栏的沉浸模式
     */
    public static final String TYPE_BACKGROUND = "type_background";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除系统titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setView(layoutResID);
    }

    /**
     * 将状态栏添加至布局中
     * @param viewGroup
     */
    private void addStatusBar(ViewGroup viewGroup) {
        mStatusBar = new View(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight());
        mStatusBar.setLayoutParams(lp);
        mStatusBar.setBackgroundColor(mStatusBarColor);
        viewGroup.addView(mStatusBar);
    }

    /**
     * 获取状态栏高度
     * @return
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    protected void setView(View contentView){
        //去掉状态栏布局
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //当API>=21时，状态栏会自动增加一块半透明色块，这段代码将其设为透明色
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
            if (immersionType.equals(TYPE_LAYOUT)) {
                LinearLayout ll_content = new LinearLayout(this);
                ll_content.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                ll_content.setOrientation(LinearLayout.VERTICAL);
                addStatusBar(ll_content);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                ll_content.addView(contentView, lp);
                setContentView(ll_content);
            } else if (immersionType.equals(TYPE_BACKGROUND)) {
                contentView.setPadding(0, getStatusBarHeight(), 0, 0);
                setContentView(contentView);
            }

        }else{
            setContentView(contentView);
        }
    }

    /**
     * 子类设置布局时应调用该方法
     * @param resId 布局id
     */
    protected void setView(int resId){
        View contentView = View.inflate(this, resId, null);
        setView(contentView);
    }

    /**
     * 子类设置布局时应调用该方法
     * @param resId 布局id
     * @param color 状态栏颜色
     */
    protected void setView(int resId, int color){
        mStatusBarColor = color;
        setView(resId);
    }

    /**
     * 子类设置布局时应调用该方法
     * @param contentView 布局View
     * @param color 状态栏颜色
     */
    protected void setView(View contentView, int color){
        mStatusBarColor = color;
        setView(contentView);
    }

    protected void setStatusBarColor(int color){
        if(mStatusBar != null) {
            mStatusBar.setBackgroundColor(color);
        }
    }

    /**
     *
     * @param contentView 布局View
     * @param type 浸入模式:TYPE_BACKGROUND或者TYPE_LAYOUT
     */
    protected void setView(View contentView, String type){
        immersionType = type;
        setView(contentView);
    }

    /**
     *
     * @param resId 布局Id
     * @param type 浸入模式:TYPE_BACKGROUND或者TYPE_LAYOUT
     */
    protected void setView(int resId, String type){
        immersionType = type;
        setView(resId);
    }
}
