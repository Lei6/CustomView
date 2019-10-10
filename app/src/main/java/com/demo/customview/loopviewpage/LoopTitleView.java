package com.demo.customview.loopviewpage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * created by 姚明亮
 * Time：2019/10/10 10:12
 */
@SuppressLint("AppCompatCustomView")
public class LoopTitleView extends TextView {
    public LoopTitleView(Context context) {
        super(context);
    }

    public LoopTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoopTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
