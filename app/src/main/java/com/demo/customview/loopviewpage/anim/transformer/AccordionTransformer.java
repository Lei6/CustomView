package com.demo.customview.loopviewpage.anim.transformer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * created by 姚明亮
 * Time：2019/10/10 10:24
 * <p>
 * 折叠
 */
public class AccordionTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        page.setPivotX(position <= 0 ? page.getMeasuredWidth() : 0);
        page.setScaleX(position <= 0 ? 1f + position : 1f - position);
    }
}
