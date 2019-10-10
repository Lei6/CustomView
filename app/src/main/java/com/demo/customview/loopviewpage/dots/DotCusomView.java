package com.demo.customview.loopviewpage.dots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * created by 姚明亮
 * Time：2019/10/10 09:51
 */
public abstract class DotCusomView extends View {
    private int color;

    public DotCusomView(Context context) {
        super(context);
    }

    public DotCusomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DotCusomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        customDraw(canvas,paint);
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        this.color = color;
        invalidate();
    }

    protected abstract void customDraw(Canvas canvas, Paint paint);
}
