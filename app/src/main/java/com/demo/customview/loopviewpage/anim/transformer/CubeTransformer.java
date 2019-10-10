package com.demo.customview.loopviewpage.anim.transformer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * created by 姚明亮
 * Time：2019/10/10 10:33
 */
public class CubeTransformer implements ViewPager.PageTransformer{
    @Override
    public void transformPage(@NonNull View view, float position) {
        view.setPivotX(position <= 0 ? view.getMeasuredWidth() : 0);
        view.setPivotY(view.getMeasuredHeight() * 0.5f);
        view.setRotationY(90f * position);
    }
}
