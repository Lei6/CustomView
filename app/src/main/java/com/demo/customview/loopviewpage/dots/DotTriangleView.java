package com.demo.customview.loopviewpage.dots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * created by 姚明亮
 * Time：2019/10/10 09:57
 */
public class DotTriangleView extends DotCusomView{
    public DotTriangleView(Context context) {
        super(context);
    }

    @Override
    protected void customDraw(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(getWidth()/2,0);
        path.lineTo(0,getHeight());
        path.lineTo(getWidth(),getHeight());
        path.lineTo(getWidth()/2,0);
        canvas.drawPath(path,paint);
    }
}
