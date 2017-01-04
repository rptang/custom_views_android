package com.project.rptang.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.project.rptang.android.switch_button.SwitchButton;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout mFrameLayout = (FrameLayout) findViewById(R.id.container);

        SwitchButton mSwitchButton = new SwitchButton(this);
        mSwitchButton.setThumbDrawable(getResources().getDrawable(R.drawable.jianshihuamian_shuixiangwendu_on2_sel), getResources().getDrawable(R.drawable.jianshihuamian_shuixiangwendu_off2_nor));
        mSwitchButton.setTrackDrawable(getResources().getDrawable(R.drawable.jianshihuamian_shuixiangwendu_on1_sel), getResources().getDrawable(R.drawable.jianshihuamian_shuixiangwendu_off1_nor));
        mSwitchButton.setOnStateChangeListener(new SwitchButton.OnStateChangeListener() {
            @Override
            public void stateChange(boolean state) {
                Log.d(TAG, "stateChange: "+state);
            }
        });
        mFrameLayout.addView(mSwitchButton);

    }
}
