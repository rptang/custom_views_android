package com.project.rptang.android.top_bar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.project.rptang.android.R;

public class Main2Activity extends TopBarBaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_main2;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("陈序员");

        setTopLeftButton(R.drawable.ic_return_white_24dp, new OnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(Main2Activity.this, "陈序员点击了左上角按钮！", Toast.LENGTH_LONG).show();
            }
        });

        setTopRightButton("Button", new OnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(Main2Activity.this, "点击了右上角按钮！", Toast.LENGTH_LONG).show();
            }
        });
    }
}
