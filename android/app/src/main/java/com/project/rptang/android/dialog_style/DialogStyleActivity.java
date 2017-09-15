package com.project.rptang.android.dialog_style;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.project.rptang.android.R;

import static com.project.rptang.android.dialog_style.Effectstype.RotateBottom;


public class DialogStyleActivity extends AppCompatActivity {

    /**
     * Fadein, Slideleft, Slidetop, SlideBottom, Slideright, Fall, Newspager, Fliph, Flipv, RotateBottom, RotateLeft, Slit, Shake, Sidefill
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_style);

        NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(this);

        dialogBuilder
                .withEffect(RotateBottom)                             //def gone
                .withContentDrawableAndPosition(getResources().getDrawable(R.drawable.zongdianyuan_tk_bg),100,100)
                .withLeftButtonDrawable(getResources().getDrawable(R.drawable.zongdianyuan_tk_gb_btn_nor),getResources().getDrawable(R.drawable.zongdianyuan_tk_gb_btn_pre))
                .withRightButtonDrawable(getResources().getDrawable(R.drawable.zongdianyuan_tk_qd_btn_nor),getResources().getDrawable(R.drawable.zongdianyuan_tk_qd_btn_pre))
                .setCancelTimeout(5)
                .setLeftButtonClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
                    }
                })
                .setRightButtonClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(),"i'm btn2",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    /*@Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        View view = getWindow().getDecorView();
        WindowManager.LayoutParams lp = (WindowManager.LayoutParams) view.getLayoutParams();
        lp.gravity = Gravity.LEFT | Gravity.TOP;
        lp.x = 10;
        lp.y = 10;
        lp.width = 300;
        lp.height = 300;
        getWindowManager().updateViewLayout(view, lp);

    }*/
}
