package com.demo.customview.window_manager;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.demo.customview.R;

/**
 * Author by YML, Date on 2021/3/30.
 * PS: Not easy to write code, please indicate.
 */
public class FloatWindowView extends LinearLayout {

    // 系统状态栏的高度
    private static int mStatusBarHeight;
    // 用于更新小悬浮窗的位置
    private WindowManager mWindowManager;
    // 小悬浮窗的布局参数
    public WindowManager.LayoutParams mWindowParams;

    // 记录手指按下时在屏幕上的横坐标,用来判断单击事件
    private float xDownInScreen;
    // 记录手指按下时在屏幕上的纵坐标,用来判断单击事件
    private float yDownInScreen;

    // 记录当前手指位置在屏幕上的横坐标
    private float xInScreen;
    // 记录当前手指位置在屏幕上的纵坐标
    private float yInScreen;

    // 记录手指按下时在小悬浮窗的View上的横坐标
    private float xInView;
    // 记录手指按下时在小悬浮窗的View上的纵坐标
    private float yInView;
    private float mCurrentX;
    private float mCurrentY;

    public FloatWindowView(Context context) {
        this(context,null);
    }

    public FloatWindowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FloatWindowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.floating_window_layout, this);
        Log.e("yml", "init: ------>" );
        mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mStatusBarHeight = getStatusBarHeight();

        /*初始化window params*/
        mWindowParams = new WindowManager.LayoutParams();
        // 设置显示类型为overlay
        mWindowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        // 显示图片格式
        mWindowParams.format = PixelFormat.RGBA_8888;
        // 设置对齐方式为中上
        mWindowParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
        // 设置交互模式
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowParams.x = 0;
        mWindowParams.y = 0;
        mCurrentX = 0;
        mCurrentY = 0;

    }

    /**
     * 获取状态栏的高度
     * @return height of statusBar
     */
    private int getStatusBarHeight() {
        Rect frame = new Rect();
        getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }
}
