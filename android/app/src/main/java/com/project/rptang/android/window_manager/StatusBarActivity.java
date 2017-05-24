package com.project.rptang.android.window_manager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.rptang.android.R;

public class StatusBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);

        StatusBarCompat.compat(this);
    }
}
