package com.demo.customview.sort;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.demo.customview.CommonUtils;
import com.demo.customview.R;

import java.util.List;

/**
 * created by 姚明亮
 * Time：2019/8/20 10:34
 */
public class SideBarView extends View {

    public final static int TYPE_NORMAL = 0, TYPE_CENTER = 1;
    private final static int DEFAULT_MAX_COUNT = 29;
    private final static String[] DEFAULT_LETTERS = {"↑", "☆", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private String[] mLetters;
    private String[] mDefaultLetters;
    private int mMaxCount;
    private int mDefaultMaxCount;
    private int mType;
    private int mCount;
    private int mWidthRect;
    private int mRectRadius;
    private int mColorTrans;
    private int mColorWhite;
    private int mColorBar;
    private int mColorRect;
    private Rect mRect;
    private RectF mRectf;
    private Paint mPaint;
    private Paint mPaintCur;
    private Paint mPaintRect;
    private float mOnepice;
    private int mHeight;
    private int mWidth;
    private int mWidthBar;
    private float textSize;
    private float mTextHeight;
    private float mTextLightHeight;
    private int mIndex = -1;
    private boolean mDValid;
    private OnLetterChangedListener mListener;
    public SideBarView(Context context) {
        this(context, null);
    }

    public SideBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ui_view_SideBar);
        int mType = typedArray.getInt(R.styleable.ui_view_SideBar_ui_view_sidebar_type, TYPE_NORMAL);
        mDefaultMaxCount = typedArray.getInt(R.styleable.ui_view_SideBar_ui_view_sidebar_maxCount, DEFAULT_MAX_COUNT);
        String strLetters = typedArray.getString(R.styleable.ui_view_SideBar_ui_view_sidebar_letters);

        if (TextUtils.isEmpty(strLetters)) {
            mLetters = mDefaultLetters = DEFAULT_LETTERS;
        } else {
            mLetters = mDefaultLetters = strLetters.split(";");
        }
        mMaxCount = Math.max(mDefaultMaxCount, mLetters.length);
        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        if (mType == TYPE_CENTER) {
            mLetters = new String[]{};
        }
        mCount = mLetters.length;
        mWidthRect = CommonUtils.dip2px(context, 70);
        mRectRadius = CommonUtils.dip2px(context, 6);
        mColorTrans = Color.parseColor("#00000000");
        mColorWhite = Color.parseColor("#ffffff");
        mColorBar = Color.parseColor("#aaBBBBBB");
        mColorRect = Color.parseColor("#aa7F7F7F");
        mRect = new Rect();
        mRectf = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(Color.parseColor("#565656"));

        mPaintCur = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCur.setTextAlign(Paint.Align.CENTER);
        mPaintCur.setColor(Color.parseColor("#008577"));

        mPaintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintRect.setTextAlign(Paint.Align.CENTER);
        mPaintRect.setTextSize(CommonUtils.dip2px(context, 32));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mOnepice = 1f * mHeight / mMaxCount;
        mWidthBar = (int) (mOnepice * 1.182f);
        textSize = mOnepice * 0.686f;
        mPaint.setTextSize(textSize);
        mPaintCur.setTextSize(textSize);
        mTextHeight = CommonUtils.getTextHeight(mPaint);
        mTextLightHeight = CommonUtils.getTextHeight(mPaintRect);
        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mType == TYPE_NORMAL) {
            resetRect(mWidth - mWidthBar,0,mWidth,mHeight,mIndex==-1?mColorTrans:mColorBar);
            canvas.drawRect(mRectf,mPaintRect);
            for (int i = 0; i < mCount; i++) {
                canvas.drawText(mLetters[i],mWidth-mWidthRect/2,mOnepice*i+mOnepice/2+mTextHeight/2,i==mIndex?mPaintCur:mPaint);
            }
            if (mIndex>=0&&mIndex<mCount){
                resetRect((mWidth-mWidthRect)/2,(mHeight-mWidthRect)/2,(mHeight+mWidthRect)/2,(mHeight+mWidthRect)/2,mColorRect);
                canvas.drawRoundRect(mRectf,mRectRadius,mRectRadius,mPaintRect);
                mPaintRect.setColor(mColorWhite);
                canvas.drawText(mLetters[mIndex],mWidth/2,(mHeight+mTextLightHeight)/2,mPaintRect);
            }
        }else {
            super.onDraw(canvas);
            float offsetY = mHeight*(1-1f*mCount/mMaxCount)/2;
            float endY = offsetY+mOnepice*mCount;
            for (int i = 0; i < mCount; i++) {
                canvas.drawText(mLetters[i],mWidth-mWidthBar/2,offsetY+mOnepice*i+mOnepice/2+mTextHeight/2,i==mIndex?mPaintCur:mPaint);
            }
        }
    }

    private void resetRect(int i, int i1, int mWidth, int mHeight, int i2) {
        mRect.set(i,i1,mWidth,mHeight);
        mRectf.set(mRect);
        mPaintRect.setColor(i2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float offsetY = mType == TYPE_CENTER?mHeight*(1-1f*mCount/mMaxCount)/2:0;
        float eX = event.getX();
        float eY = event.getY()-offsetY;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDValid = eY>=0&&eY<=mOnepice*mCount+1&&eX>mWidth-mWidthBar;
                return mDValid &&delegate(adjustIndex(eY));
            case MotionEvent.ACTION_MOVE:
                return delegate(adjustIndex(eY));
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                return delegate(-1);
        }
        return super.onTouchEvent(event);
    }

    private boolean delegate(int adjustIndex) {
        if (mDValid&&adjustIndex!=mIndex){
            mIndex = adjustIndex;
            if (mIndex!=-1&&mListener!=null){
                mListener.onChange(mIndex,mLetters[mIndex]);
            }
            invalidate();
            return true;
        }
        return false;
    }

    private int adjustIndex(float eY) {
        eY = Math.max(eY,0);
        eY = Math.min(eY,mHeight);
        int i = (int)(eY/mOnepice);
        i=Math.max(i,0);
        i = Math.min(i,mCount-1);
        return i;
    }
    public interface OnLetterChangedListener {
        void onChange(int index, String c);
    }

    public void setOnLetterChangedListener(OnLetterChangedListener listener) {
        this.mListener = listener;
    }

    public void setType(int type) {
        mType = type;
    }

    public void reset(List<String> datas) {
        if (datas == null) {
            return;
        }
        mLetters = mType == TYPE_CENTER ? datas.toArray(new String[datas.size()])
                : mDefaultLetters;
        mMaxCount = Math.max(mDefaultMaxCount, mLetters.length);
        mCount = mLetters.length;
        requestLayout();
        invalidate();
    }
}
