package com.project.rptang.android.wheel_picker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.project.rptang.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stiven on 2017/1/9.
 */

public class WheelPicker extends ScrollView {
    private static final String TAG = "WheelPicker";
    /**
     * 整个控件的宽、高
     */
    private int width, height;
    /**
     * 单元格的宽度
     */
    private int unitWidth = 100;
    /**
     * 单元格的高度
     */
    private int unitHeight = 70;
    /**
     * 未选中的字体大小
     */
    private float normalFont = 32.0f;
    /**
     * 选中的字体大小
     */
    private float selectedFont = 34.0f;
    /**
     * 字体的线条宽度
     */
    private float lineWidth = 2.0f;
    /**
     * 未选中的字体颜色
     */
    private int normalColor = 0xff5A5A5A;
    /**
     * 选中的字体颜色
     */
    private int selectedColor = 0xffB4B4B4;
    /**
     * 未选中的字体样式
     */
    private Typeface norTypeFace = Typeface.DEFAULT;
    /**
     * 选中的字体样式
     */
    private Typeface selTypeFace = Typeface.DEFAULT;
    /**
     * 单元格对象集合
     */
    private List<TextItem> textItemList = new ArrayList<>();
    /**
     * 数据集合
     */
    private List<String> dataList = new ArrayList<>();
    /**
     * 辅助变量，用于控制滑动
     */
    private float down;
    /**
     * 辅助变量，用于控制数字归位
     */
    private float minDistance;
    /**
     * 辅助变量，用于获得当前选中的item位置
     */
    private int selectedId;
    /**
     * 显示item个数，要求奇数
     */
    private int visableNumbers = 5;
    /**
     * 第一次进来，选择集合中的哪一个
     */
    private int selectPosition;
    /**
     * 是否在滑动
     */
    private boolean isMoving = false;
    /**
     * 选择item监听器
     */
    private OnItemPickListener onItemPickListener;

    public WheelPicker(Context context) {
        this(context, null);
    }

    public WheelPicker(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public WheelPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WheelPicker);
        unitWidth = array.getInteger(R.styleable.WheelPicker_unitWidth,100);
        unitHeight = (int) array.getDimension(R.styleable.WheelPicker_unitHeight,70);
        visableNumbers = array.getInteger(R.styleable.WheelPicker_visableNumber,3);
        selectPosition = array.getInteger(R.styleable.WheelPicker_selectPosition,2);
        normalFont = array.getDimension(R.styleable.WheelPicker_normalTextColor,32.0f);
        selectedFont = array.getDimension(R.styleable.WheelPicker_selectedTextSize,34.0f);
        normalColor = array.getColor(R.styleable.WheelPicker_normalTextColor,0xff5A5A5A);
        selectedColor = array.getColor(R.styleable.WheelPicker_selectedTextColor,0xffB4B4B4);
        array.recycle();

    }

    private void initData() {
        width = visableNumbers * unitWidth;

        if (textItemList == null) return;

        textItemList.clear();
        for (int i = 0; i < dataList.size(); i++) {
            TextItem mTextItem = new TextItem();
            mTextItem.id = i;
            mTextItem.textContent = dataList.get(i);
            mTextItem.x = i * unitWidth;
            mTextItem.y = 0;
            textItemList.add(mTextItem);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = visableNumbers * unitWidth;
        height = unitHeight;
        setMeasuredDimension(width, unitHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTextItem(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                down = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                isMoving = true;
                moveDistance(event.getX() - down);
                down = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                if(isMoving == true){
                    moveToSelectedItem();
                }else {

                }
                isMoving = false;
                break;
        }
        return true;
    }

    private void moveToSelectedItem() {

        for (TextItem mTextItem : textItemList) {
            if(minDistance == 0){
                minDistance = Math.abs(mTextItem.x - ((width - unitWidth) / 2));
                selectedId = mTextItem.id;
            }else {
                if(minDistance > Math.abs(mTextItem.x - ((width - unitWidth) / 2))){
                    selectedId = mTextItem.id;
                    minDistance = Math.abs(mTextItem.x - ((width - unitWidth) / 2));
                }
            }
        }

        minDistance = ((width - unitWidth) / 2) - textItemList.get(selectedId).x;

        for(TextItem mTextItem : textItemList){
            mTextItem.x = mTextItem.x + minDistance;
        }
        minDistance = 0;

        postInvalidate();
    }

    private void moveDistance(float moveDistance) {

        for (TextItem mTextItem : textItemList) {
            mTextItem.moveDistance = moveDistance;
            mTextItem.x = mTextItem.x + moveDistance;
        }
        postInvalidate();
    }

    private void drawTextItem(Canvas canvas) {

        for (TextItem mTextItem : textItemList) {
            mTextItem.drawItem(canvas);
        }
    }

    public class TextItem {

        public int id;
        public String textContent = "";
        public float x, y;
        public TextPaint textPaint;
        public Rect textRect;
        public float moveDistance;


        public void drawItem(Canvas canvas) {

            if (textPaint == null) {
                textPaint = new TextPaint();
                textPaint.setAntiAlias(true);
            }
            if (textRect == null) {
                textRect = new Rect();
            }

            if(isSelected()){
                textPaint.setColor(selectedColor);
                textPaint.setTypeface(selTypeFace);
                textPaint.setTextSize(selectedFont);
            }else {
                textPaint.setColor(normalColor);
                textPaint.setTypeface(norTypeFace);
                textPaint.setTextSize(normalFont);
            }
            /**
             * 画布画文字与画图片是不一样的，需要先计算包含文字的最小矩形，得到它的宽和高，用下面的方法
             */
            textContent = (String) TextUtils.ellipsize(textContent, textPaint, unitWidth, TextUtils.TruncateAt.END);
            textPaint.getTextBounds(textContent, 0, textContent.length(), textRect);
            canvas.drawText(textContent, x + unitWidth / 2 - textRect.width() / 2 - lineWidth,
                    (unitHeight + textRect.height()) / 2, textPaint);
        }

        public boolean isSelected() {
            if (x == ((width - unitWidth) / 2)) {
                if(onItemPickListener != null){
                    onItemPickListener.itemPick(id,textContent);
                }
                return true;
            }else {
                return false;
            }
        }
    }

    public interface OnItemPickListener{
        void itemPick(int position,String content);
    }

    public void setOnItemPickListener(WheelPicker.OnItemPickListener onItemPickListener){
        this.onItemPickListener = onItemPickListener;
    }

    public void setdataList(List<String> dataList) {
        this.dataList = dataList;
        initData();
        postInvalidate();
    }

    public void setSelectPosition(int position){

        this.selectPosition = position;
        minDistance = ((width - unitWidth) / 2) - textItemList.get(selectPosition).x;
        for(TextItem mTextItem : textItemList){
            mTextItem.x = mTextItem.x + minDistance;
        }
        minDistance = 0;
        postInvalidate();
    }

    /**
     * 设置单元格宽度、高度
     * @param unitWidth
     * @param unitHeight
     */
    public void setUnitWidthAndHeight(int unitWidth,int unitHeight){
        this.unitWidth = unitWidth;
        this.unitHeight = unitHeight;
        postInvalidate();
    }

    /**
     * 设置为选中时字体属性
     * @param size
     * @param color
     * @param typeface
     */
    public void setNormalTextAttr(int size,int color,Typeface typeface){
        this.normalFont = size;
        this.normalColor = color;
        this.norTypeFace = typeface;
        postInvalidate();
    }

    /**
     * 设置选中时字体属性
     * @param size
     * @param color
     * @param typeface
     */
    public void setSelectedTextAttr(int size,int color,Typeface typeface){
        this.selectedFont = size;
        this.selectedColor = color;
        this.selTypeFace = typeface;
        postInvalidate();
    }

    /**
     * 设置显示的item个数
     * @param numbers
     */
    public void setVisableNumbers(int numbers){
        this.visableNumbers = numbers;
        postInvalidate();
    }
}
