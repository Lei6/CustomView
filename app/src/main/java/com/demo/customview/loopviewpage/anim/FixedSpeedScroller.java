package com.demo.customview.loopviewpage.anim;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * created by 姚明亮
 * Time：2019/10/10 10:21
 */
public class FixedSpeedScroller extends Scroller {

    private int mDuration;
    public void setmDuration(int mDuration){
        this.mDuration = mDuration;
    }

    public FixedSpeedScroller(Context context) {
        super(context);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}
