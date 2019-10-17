package com.demo.customview.loopviewpage.anim.transformer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * created by 姚明亮
 * Time：2019/10/10 10:27
 */
public abstract class LoopVerticalTransformer implements ViewPager.PageTransformer{
    @Override
    public void transformPage(@NonNull View page, float position) {
        tansformViewPage(page,position);
        if (position<-1){
            page.setAlpha(0);
        }else if (position<=1){
            page.setAlpha(1);
            page.setTranslationX(-position * page.getMeasuredWidth());
            page.setTranslationY(position * page.getMeasuredHeight());
        }else {
            page.setAlpha(0);
        }
    }

    protected abstract void tansformViewPage(View page, float position);
}
