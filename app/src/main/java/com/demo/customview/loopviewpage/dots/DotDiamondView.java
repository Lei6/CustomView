package com.demo.customview.loopviewpage.dots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * created by 姚明亮
 * Time：2019/10/10 10:01
 */
public class DotDiamondView extends DotCusomView {


    public DotDiamondView(Context context) {
        super(context);
    }

    @Override
    protected void customDraw(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(getWidth()/2,0);
        path.lineTo(0,getHeight()/2);
        path.lineTo(getWidth()/2,getHeight());
        path.lineTo(getWidth(),getHeight()/2);
        path.lineTo(getWidth()/2,0);
        canvas.drawPath(path,paint);
    }
}
