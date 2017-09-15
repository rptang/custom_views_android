package com.project.rptang.android.dialog_style;


import com.project.rptang.android.dialog_style.effects.BaseEffects;
import com.project.rptang.android.dialog_style.effects.FadeIn;
import com.project.rptang.android.dialog_style.effects.FlipH;
import com.project.rptang.android.dialog_style.effects.FlipV;
import com.project.rptang.android.dialog_style.effects.NewsPaper;
import com.project.rptang.android.dialog_style.effects.SideFall;
import com.project.rptang.android.dialog_style.effects.SlideLeft;
import com.project.rptang.android.dialog_style.effects.SlideRight;
import com.project.rptang.android.dialog_style.effects.SlideTop;
import com.project.rptang.android.dialog_style.effects.SlideBottom;
import com.project.rptang.android.dialog_style.effects.Fall;
import com.project.rptang.android.dialog_style.effects.RotateBottom;
import com.project.rptang.android.dialog_style.effects.RotateLeft;
import com.project.rptang.android.dialog_style.effects.Slit;
import com.project.rptang.android.dialog_style.effects.Shake;

/**
 * android
 * com.project.rptang.android.dialog_style
 * Effectstype
 * <p>
 * Created by Stiven on 2017/7/14.
 * Copyright © 2017 ZYZS-TECH. All rights reserved.
 */
public enum  Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);
    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects=null;
	try {
		bEffects = effectsClazz.newInstance();
	} catch (ClassCastException e) {
		throw new Error("Can not init animatorClazz instance");
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		throw new Error("Can not init animatorClazz instance");
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		throw new Error("Can not init animatorClazz instance");
	}
	return bEffects;
    }
}
