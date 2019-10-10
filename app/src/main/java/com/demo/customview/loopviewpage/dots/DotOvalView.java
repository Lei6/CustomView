package com.demo.customview.loopviewpage.dots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * created by 姚明亮
 * Time：2019/10/10 09:50
 */
public class DotOvalView extends DotCusomView{

    public DotOvalView(Context context) {
        super(context);
    }

    @Override
    protected void customDraw(Canvas canvas, Paint paint) {
        canvas.drawOval(new RectF(0,0,getWidth(),getHeight()),paint);
    }
}
