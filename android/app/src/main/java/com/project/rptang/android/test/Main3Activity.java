package com.project.rptang.android.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.project.rptang.android.R;

public class Main3Activity extends AppCompatActivity {

    private int i = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (i){
                    case 2:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a02));
                        i = 3;
                        break;
                    case 3:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a03));
                        i = 4;
                        break;
                    case 4:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a04));
                        i = 5;
                        break;
                    case 5:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a05));
                        i = 6;
                        break;
                    case 6:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a06));
                        i = 7;
                        break;
                    case 7:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a07));
                        i = 8;
                        break;
                    case 8:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a08));
                        i = 9;
                        break;
                    case 9:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a09));
                        i = 10;
                        break;
                    case 10:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a10));
                        i = 11;
                        break;
                    case 11:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a11));
                        i = 12;
                        break;
                    case 12:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a12));
                        i = 13;
                        break;
                    case 13:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a13));
                        i = 14;
                        break;
                    case 14:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a14));
                        i = 15;
                        break;
                    case 15:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a15));
                        i = 16;
                        break;
                    case 16:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a16));
                        i = 17;
                        break;
                    case 17:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a17));
                        i = 18;
                        break;
                    case 18:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a18));
                        i = 19;
                        break;
                    case 19:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a19));
                        i = 20;
                        break;
                    case 20:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a20));
                        i = 21;
                        break;
                    case 21:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a21));
                        i = 22;
                        break;
                    case 22:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a22));
                        i = 23;
                        break;
                    case 23:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a23));
                        i = 24;
                        break;
                    case 24:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a24));
                        i = 25;
                        break;
                    case 25:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a25));
                        i = 26;
                        break;
                    case 26:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a26));
                        i = 27;
                        break;
                    case 27:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a27));
                        i = 28;
                        break;
                    case 28:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.a28));
                        i = 2;
                        break;

                }
            }
        });
    }
}
