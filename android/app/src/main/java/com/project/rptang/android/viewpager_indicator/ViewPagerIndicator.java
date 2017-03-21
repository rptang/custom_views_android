package com.project.rptang.android.viewpager_indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.project.rptang.android.R;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoLayoutHelper;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by Stiven on 2017/2/23.
 */

public class ViewPagerIndicator extends LinearLayout {

    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    private static final String TAG = "ViewPagerIndicator";

    //绘制三角形的画笔
    private Paint mPaint;
    //path构成一个三角形
    private Path mPath;
    //三角形的宽度
    private int mTriangleWidth;
    //三角形的高度
    private int mTriangleHeigth;
    //三角形的宽度为单个组件的1/6
    private static final float RADIO_TRIANGLE_WIDTH = 1 / 6F;
    //三角星的最大宽度
    private final int DIMENSION_TRIANGLE_WIDTH_MAX = (int) (getScreenWidth()/3 * RADIO_TRIANGLE_WIDTH);
    //三角形的初始偏位置
    private int mInitTranslationX;
    //移动时的便宜位置
    private int mTranslationX;

    private int mTabVisiableCount;//页面可见数量
    private static final int COUNT_DEFAULT_TAB = 4;//默认数量

    private List<String> mTitles;

    private static final int COLOR_TEXT_NORMAL = 0x77FFFFFF;
    private static final int COLOR_TEXT_HIGHLIGHT = 0xFFFFFFFF;



    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

//获得自定义属性，tab的数量
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);//这里是获取布局文件里的属性
        mTabVisiableCount = a.getInt(R.styleable.ViewPagerIndicator_visiable_tab_count,COUNT_DEFAULT_TAB);
        if (mTabVisiableCount < 0) {
            mTabVisiableCount = COUNT_DEFAULT_TAB;
        }
        a.recycle();

//初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffffff"));
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setPathEffect(new CornerPathEffect(5));

    }

    //这个方法为什么在构造方法之后就执行呢？
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

//这是三角形的底部宽度，整个屏幕的宽度的1/3*1/6，因为只显示3个组件
        mTriangleWidth = (int) (w / mTabVisiableCount * RADIO_TRIANGLE_WIDTH);
//        mTriangleWidth = Math.min(mTriangleWidth,DIMENSION_TRIANGLE_WIDTH_MAX);
        mTriangleWidth = AutoUtils.getPercentWidthSize(19);
//要想三角形显示在组件的中间下方，左边点得偏移，偏移多少，先定到一个组件的中间位置，在往左移动半个三角形的宽度
        mInitTranslationX = w / mTabVisiableCount / 2 - mTriangleWidth / 2;
//初始化三角形
        initTriangle();

    }

    /*当xml加载完成以后，会回调这个方法*
         *设置布局中view的一些必要属性，如果设置了setTabTitles,布局中view则无效
         */
    @Override
    protected void onFinishInflate() {

        int cCount = getChildCount();
        if (cCount == 0) {
            return;
        }
        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LinearLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
            lp.weight = 0;
            lp.width = getScreenWidth()/mTabVisiableCount;
            view.setLayoutParams(lp);
        }
        setItemClickEvent();
    }

    /**
     * 获得屏幕的宽度
     * @return
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    //绘制三角形，这个是在onSizeChanged方法后执行的
    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.save();
        canvas.translate(mInitTranslationX + mTranslationX, getHeight());
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);

    }

    /**
     * 初始化三角形
     */
    private void initTriangle() {

//        mTriangleHeigth = mTriangleWidth / 2;
        mTriangleHeigth = AutoUtils.getPercentHeightSize(19);
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeigth);
        mPath.close();

    }

    /**
     * 指示器跟随手指进行滚动
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {

        int tabWidth = getWidth()/mTabVisiableCount;
        mTranslationX = (int) (tabWidth*(position+offset));

//容器移动，当tab处于移动至最后一个时
        if(position >= (mTabVisiableCount-2)&&offset>0&&getChildCount()>mTabVisiableCount){
            if(mTabVisiableCount!=1){
                this.scrollTo((position-(mTabVisiableCount-2))*tabWidth+(int)(tabWidth*offset), 0);
            }else {
                this.scrollTo(position*tabWidth+(int)(tabWidth*offset),0);
            }
        }
        invalidate();//重绘制三角形
    }

    public void setTabItemTitles(List<String> titles){

        if(titles!=null&&titles.size()>0){
            this.removeAllViews();
            mTitles = titles;
            for(String title:mTitles){
                addView(generateTextView(title));
            }

            setItemClickEvent();
        }
    }


    /**
     * 设置可见的Tab数量
     * @param count
     */
    public void setVisiableTabCount(int count){
        mTabVisiableCount = count;
    }
    /**
     * 根据title创建Tab
     * @param title
     * @return
     */
    private View generateTextView(String title) {
        TextView tv = new TextView(getContext());
        AutoLinearLayout.LayoutParams lp = (AutoLinearLayout.LayoutParams) new AutoLinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth()/mTabVisiableCount;
        lp.setMargins(0,0,0,AutoUtils.getPercentHeightSize(20));
        tv.setText(title);
        tv.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, AutoUtils.getPercentHeightSize(36));
        tv.setTextColor(COLOR_TEXT_NORMAL);
        tv.setLayoutParams(lp);
        return tv;
    }

    private ViewPager mViewPager;

    public PageOnchangeListener mListener;

    public interface PageOnchangeListener{

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
        public void onPageSelected(int position);
        public void onPageScrollStateChanged(int state);
    }

    //将占用的接口暴露给用于使用
    public void setOnPageOnchangeListener(PageOnchangeListener listener){
        this.mListener = listener;
    }

    /**
     * 设置关联的ViewPager
     * @param viewPager
     * @param pos
     */
    public void setViewPager(ViewPager viewPager,int pos){
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//那么偏移量怎么算：tabWidth*positionOffset+tabWidth*position
                scroll(position, positionOffset);
                if(mListener!=null){
                    mListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(mListener!=null){
                    mListener.onPageSelected(position);
                }
                highLightTextView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(mListener!=null){
                    mListener.onPageScrollStateChanged(state);
                }
            }
        });
        mViewPager.setCurrentItem(pos);
        highLightTextView(pos);
    }

    /**
     *高亮某个Tab的文本
     * @param pos
     */
    private void highLightTextView(int pos){
        resetTextViewColor();
        View view = getChildAt(pos);
        if(view instanceof TextView){
            ((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHT);
        }
    }

    /**
     * 重置Tab的颜色
     */
    private void resetTextViewColor(){
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if(view instanceof TextView){
                ((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
            }
        }
    }

    private void setItemClickEvent(){
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final int j = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(j);
                }
            });
        }
    }

    @Override
    public AutoLinearLayout.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new AutoLinearLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode())
        {
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
