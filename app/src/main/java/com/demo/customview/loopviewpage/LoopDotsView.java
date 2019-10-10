package com.demo.customview.loopviewpage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.demo.customview.R;
import com.demo.customview.loopviewpage.dots.DotDefault;
import com.demo.customview.loopviewpage.dots.DotDiamondView;
import com.demo.customview.loopviewpage.dots.DotOvalView;
import com.demo.customview.loopviewpage.dots.DotStyle;
import com.demo.customview.loopviewpage.dots.DotTriangleView;

/**
 * created by 姚明亮
 * Time：2019/10/10 09:29
 */
public class LoopDotsView extends LinearLayout {

    private int dotSelectResource;
    private int dotResource;
    private int dotSelectColor;
    private int dotColor;
    private int dotShape;
    private int dotRange;
    private int dotHeight;
    private int dotWidth;
    private int dotSize;

    public LoopDotsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoopDotsView);
        dotSize = (int) typedArray.getDimension(R.styleable.LoopDotsView_dotSize, 0);
        dotWidth = (int) typedArray.getDimension(R.styleable.LoopDotsView_dotWidth, 0);
        dotHeight = (int) typedArray.getDimension(R.styleable.LoopDotsView_dotHeight, 0);
        dotRange = (int) typedArray.getDimension(R.styleable.LoopDotsView_dotRange, 0);
        dotShape = typedArray.getInt(R.styleable.LoopDotsView_dotShape, DotDefault.dotShape);
        dotColor = typedArray.getColor(R.styleable.LoopDotsView_dotColor, DotDefault.dotColor);
        dotSelectColor = typedArray.getColor(R.styleable.LoopDotsView_dotSelectColor, DotDefault.dotSelectColor);
        dotResource = typedArray.getResourceId(R.styleable.LoopDotsView_dotResource, 0);
        dotSelectResource = typedArray.getResourceId(R.styleable.LoopDotsView_dotSelectResource, 0);
        typedArray.recycle();
    }


    public void setDotsLength(int length){
        removeAllViews();
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params.width = dotWidth>0?dotWidth:dotSize;
        params.height = dotHeight>0?dotHeight:dotSize;
        for (int i = 0; i <length ; i++) {
            View dotView = null;
            if (dotShape== DotStyle.RECTANGLE||(dotSelectResource!=0&&dotResource!=0)){
                dotView = new View(getContext());
            }else if (dotShape==DotStyle.OVAL){
                dotView = new DotOvalView(getContext());
            }else if (dotShape==DotStyle.TRIANGLE){
                dotView = new DotTriangleView(getContext());
            }else if (dotShape==DotStyle.DIAMOND){
                dotView = new DotDiamondView(getContext());
            }
            if (i==0){
                params.setMargins(0,0,0,0);
                if (dotSelectResource!=0){
                    dotView.setBackgroundResource(dotSelectResource);
                }else {
                    dotView.setBackgroundColor(dotSelectColor);
                }
            }else {
                if (getOrientation()==VERTICAL){
                    params.setMargins(0,dotRange>0?dotRange:dotSize,0,0);
                }else {
                    params.setMargins(dotRange>0?dotRange:dotSize,0,0,0);
                }
                if (dotResource!=0){
                    dotView.setBackgroundResource(dotResource);
                }else {
                    dotView.setBackgroundColor(dotColor);
                }
            }
            dotView.setLayoutParams(params);
            addView(dotView);
        }
    }
    public void upDate(int currentIndex,int oldIndex){
        if (currentIndex>=0){
            if (dotSelectResource!=0){
                getChildAt(currentIndex).setBackgroundResource(dotSelectResource);
            }else {
                getChildAt(currentIndex).setBackgroundColor(dotSelectColor);
            }
        }
        if (oldIndex>=0){
            if (dotResource!=0){
                getChildAt(oldIndex).setBackgroundResource(dotResource);
            }else {
                getChildAt(oldIndex).setBackgroundColor(dotColor);
            }
        }
    }
}
