package com.project.rptang.android.wheel_datatime;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.project.rptang.android.R;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class TimePickerLayout extends LinearLayout {

    private WheelView mWheelYear;
    private WheelView mWheelMonth;
    private WheelView mWheelDay;
    private WheelView mWheelHour;
    private WheelView mWheelMinute;

    private Context context;
    private String year,month,day;
    private boolean flagYear = false;
    private boolean flagMonth = false;

    public TimePickerLayout(Context context) {
        this(context, null);
    }

    public TimePickerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.layout_time_picker, this);
        mWheelYear = (WheelView) findViewById(R.id.year);
        mWheelMonth = (WheelView) findViewById(R.id.month);
        mWheelDay = (WheelView) findViewById(R.id.day);
        mWheelHour = (WheelView) findViewById(R.id.hour);
        mWheelMinute = (WheelView) findViewById(R.id.minute);

        mWheelYear.setData(getYearData());
        mWheelMonth.setData(getMonthData());
        mWheelDay.setData(getDayData());
        mWheelHour.setData(getHourData());
        mWheelMinute.setData(getMinuteData());

        scroll();
    }

    private void scroll() {
        mWheelYear.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (year != text) {
                    year = text;
                    flagYear = true;
                    mWheelDay.setData(getDayData());
                    setDayChange();
                }
            }

            @Override
            public void selecting(int id, String text) {

            }
        });

        mWheelMonth.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (month != text) {
                    month = text;
                    flagMonth = true;
                    mWheelDay.setData(getDayData());
                    setDayChange();
                }
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        mWheelDay.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                day = text;
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setSystemTime();

        setDayChange();
    }

    private void setDayChange(){
        try {
            for (int i = 1; i <= dayNumber(); i++) {
                if (i < 10) {
                    if (("0" + String.valueOf(i)).equals(day)) {
                        mWheelDay.setDefault(i-1);
                        return;
                    }
                }else{
                    if (Integer.parseInt(day) > dayNumber()) {
                        mWheelDay.setDefault(dayNumber()-1);
                    }else if (String.valueOf(i).equals(day)) {
                        mWheelDay.setDefault(i-1);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTime(){
        return getYear() + getMonth() + getDay() + "." + getHour() + getMinute() + "00";
    }

    private int dayNumber(){
        if (!flagMonth) {
            month = getMonth();
        }else {
            flagMonth = !flagMonth;
        }
        switch (month){
            case "01":
            case "03":
            case "05":
            case "07":
            case "08":
            case "10":
            case "12":
                return 31;
            case "04":
            case "06":
            case "09":
            case "11":
                return 30;
            case "02":
                if (!flagYear) {
                    year = getYear();
                }else{
                    flagYear = !flagYear;
                }
                if (Integer.parseInt(year) % 4 == 0) {
                    return 29;
                }else {
                    return 28;
                }
        }
        return 0;
    }

    private ArrayList<String> getYearData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 2016; i < 2117; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getMonthData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                list.add("0" + String.valueOf(i));
            }else{
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    private ArrayList<String> getDayData() {
        //ignore condition
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= dayNumber(); i++) {
            if (i < 10) {
                list.add("0" + String.valueOf(i));
            }else{
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    public ArrayList<String> getHourData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                list.add("0" + String.valueOf(i));
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    public ArrayList<String> getMinuteData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                list.add("0" + String.valueOf(i));
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    public void setSystemTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = 2016;
        if (calendar.get(Calendar.YEAR) >= 2016){
            year = calendar.get(Calendar.YEAR);
        }
        initDate(year, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }


    public void setTimeMillis(){
        try {
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(
                    process.getOutputStream());
            os.writeBytes("setprop persist.sys.timezone GMT-8\n");
            os.writeBytes("/system/bin/date -s " + this.getTime() + "\n");
            os.writeBytes("clock -w\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getYear() {
        if (mWheelYear == null) {
            return null;
        }
        return mWheelYear.getSelectedText();
    }

    public String getMonth() {
        if (mWheelMonth == null) {
            return null;
        }
        return mWheelMonth.getSelectedText();
    }

    public String getDay() {
        if (mWheelDay == null) {
            return null;
        }
        return mWheelDay.getSelectedText();
    }

    public String getHour(){
        if (mWheelHour == null) {
            return null;
        }
        return mWheelHour.getSelectedText();
    }
    public String getMinute(){
        if (mWheelMinute == null) {
            return null;
        }
        return mWheelMinute.getSelectedText();
    }

    public void initDate(int year, int month, int day, int hour, int minute) {
        this.year = String.valueOf(year);
        if (month + 1 < 10) {
            this.month = "0" + String.valueOf(month + 1);
        } else {
            this.month = String.valueOf(month + 1);
        }
        this.day = String.valueOf(day);
        mWheelYear.setDefault(year - 2016);
        mWheelMonth.setDefault(month);
        mWheelDay.setDefault(day - 1);
        mWheelHour.setDefault(hour);
        mWheelMinute.setDefault(minute);
    }
}
