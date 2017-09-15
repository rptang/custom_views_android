package com.project.rptang.android.dialog_style;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

/**
 * android
 * com.project.rptang.android.dialog_style
 * FileUtils
 * <p>
 * Created by Stiven on 2017/7/14.
 * Copyright © 2017 ZYZS-TECH. All rights reserved.
 */

public class FileUtils {

    public static StateListDrawable addStateDrawable(Drawable normal, Drawable pressed, Drawable focus) {
        StateListDrawable sd = new StateListDrawable();
        sd.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, focus);
        sd.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
        sd.addState(new int[]{android.R.attr.state_focused}, focus);
        sd.addState(new int[]{android.R.attr.state_pressed}, pressed);
        sd.addState(new int[]{android.R.attr.state_enabled}, normal);
        sd.addState(new int[]{}, normal);
        return sd;
    }
}
