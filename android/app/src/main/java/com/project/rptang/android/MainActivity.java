package com.project.rptang.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.project.rptang.android.calendar.view.MonthView;
import com.project.rptang.android.calendar.widget.GridCalendarView;
import com.project.rptang.android.switch_button.SwitchButton;
import com.project.rptang.android.wheel_datatime.TimePickerLayout;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private SwitchButton mSwitchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout mFrameLayout = (FrameLayout) findViewById(R.id.container);
        /**
         * 滑动开关的使用
         */
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

        /**
         * 滚动选择器的使用
         */
        /*WheelPicker mWheelPicker = new WheelPicker(this);
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
        mFrameLayout.addView(mWheelPicker);*/

//        WeekView mWeekView = new WeekView(this);
//        mFrameLayout.addView(mWeekView);

        /**
         * 日历的使用
         */
//        Calendar calendar = Calendar.getInstance();
//        int currYear = calendar.get(Calendar.YEAR);
//        int currMonth = calendar.get(Calendar.MONTH)+ 1;
//        List<CalendarInfo> list = new ArrayList<CalendarInfo>();
//        list.add(new CalendarInfo(currYear,currMonth,4,"￥1200"));
//        list.add(new CalendarInfo(currYear,currMonth,6,"￥1200"));
//        list.add(new CalendarInfo(currYear,currMonth,12,"￥1200"));
//        list.add(new CalendarInfo(currYear,currMonth,16,"￥1200"));
//        list.add(new CalendarInfo(currYear,currMonth,28,"￥1200"));
//        list.add(new CalendarInfo(currYear,currMonth,1,"￥1200",1));
//        list.add(new CalendarInfo(currYear,currMonth,11,"￥1200",1));
//        list.add(new CalendarInfo(currYear,currMonth,19,"￥1200",2));
//        list.add(new CalendarInfo(currYear,currMonth,21,"￥1200",1));
        GridCalendarView gridCalendarView = new GridCalendarView(this,null);
        mFrameLayout.addView(gridCalendarView);
//        gridCalendarView.setCalendarInfos(list);
        gridCalendarView.setDateClick(new MonthView.IDateClick(){

            @Override
            public void onClickOnDate(int year, int month, int day) {
                Toast.makeText(MainActivity.this,"点击了" +  year + "-" + month + "-" + day,Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 时间选择器的使用
         */
        /*TimePickerLayout timePickerLayout = new TimePickerLayout(this);
        if (timePickerLayout != null) {
            timePickerLayout.setSystemTime();
        }
        mFrameLayout.addView(timePickerLayout);*/
    }


}
