package com.project.rptang.android;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import com.project.rptang.android.switch_button.SwitchButton;
import com.project.rptang.android.wheel_picker.WheelPicker;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private SwitchButton mSwitchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout mFrameLayout = (FrameLayout) findViewById(R.id.container);
        /*//引用方法一
        mSwitchButton = (SwitchButton) mFrameLayout.findViewById(R.id.custom_switch_button);

        //引用方法二
//        SwitchButton mSwitchButton = new SwitchButton(this);
//        mSwitchButton.setThumbDrawable(getResources().getDrawable(R.drawable.jianshihuamian_shuixiangwendu_on2_sel), getResources().getDrawable(R.drawable.jianshihuamian_shuixiangwendu_off2_nor));
//        mSwitchButton.setTrackDrawable(getResources().getDrawable(R.drawable.jianshihuamian_shuixiangwendu_on1_sel), getResources().getDrawable(R.drawable.jianshihuamian_shuixiangwendu_off1_nor));
        mSwitchButton.setOnStateChangeListener(new SwitchButton.OnStateChangeListener() {
            @Override
            public void stateChange(boolean state) {
                Log.d(TAG, "stateChange: "+state);
            }
        });
//        mFrameLayout.addView(mSwitchButton);*/

        WheelPicker mWheelPicker = new WheelPicker(this);
        mWheelPicker.setBackground(getResources().getDrawable(R.drawable.jianshihuamian_shuixiangwendu_shezhi_img2));
        ArrayList<String> dataList = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            dataList.add(String.valueOf(i));
        }
        mWheelPicker.setdataList(dataList);
        mWheelPicker.setOnItemPickListener(new WheelPicker.OnItemPickListener() {
            @Override
            public void itemPick(int position, String content) {}
        });
        mWheelPicker.setUnitWidthAndHeight(100,60);
        mWheelPicker.setVisableNumbers(5);
        mWheelPicker.setSelectPosition(4);
        mWheelPicker.setNormalTextAttr(32, 0xff5A5A5A, Typeface.DEFAULT);
        mWheelPicker.setSelectedTextAttr(34, 0xffB4B4B4, Typeface.DEFAULT);
        mFrameLayout.addView(mWheelPicker);

    }
}
