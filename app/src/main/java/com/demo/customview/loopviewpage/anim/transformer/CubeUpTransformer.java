package com.demo.customview.loopviewpage.anim.transformer;

import android.view.View;

/**
 * created by 姚明亮
 * Time：2019/10/10 10:34
 */
public class CubeUpTransformer extends LoopVerticalTransformer{
    @Override
    protected void tansformViewPage(View view, float position) {
        view.setPivotX(view.getMeasuredWidth() * 0.5f);
        view.setPivotY(position <= 0 ? view.getMeasuredHeight() : 0);
        view.setRotationX(-90f * position);
    }
}
