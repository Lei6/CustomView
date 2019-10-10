package com.demo.customview.loopviewpage.anim.transformer;

import android.view.View;

/**
 * created by 姚明亮
 * Time：2019/10/10 10:27
 */
public class AccordionUpTransformer extends LoopVerticalTransformer{
    @Override
    protected void tansformViewPage(View page, float position) {
        page.setPivotY(position <= 0 ? page.getMeasuredHeight() : 0);
        page.setScaleY(position <= 0 ? 1f + position : 1f - position);
    }
}
