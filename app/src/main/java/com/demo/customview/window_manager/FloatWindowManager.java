package com.demo.customview.window_manager;

import android.content.Context;
import android.view.WindowManager;

/**
 * Author by YML, Date on 2021/3/30.
 * PS: Not easy to write code, please indicate.
 */
public class FloatWindowManager {

    private Context context;

    private WindowManager mWindowManager;

    private FloatWindowView floatWindowView;


    public FloatWindowManager(Context context) {
        this.context = context;
    }


    /**
     * 创建悬浮窗
     */
    public void createFloatWindow() {
        if (floatWindowView == null) {
            floatWindowView = new FloatWindowView(context);
            getWindowManager().addView(floatWindowView, floatWindowView.mWindowParams);
        }
    }

    /**
     * 将小悬浮窗从屏幕上移除
     **/
    public void removeFloatWindow() {
        if (floatWindowView != null) {
            WindowManager windowManager = getWindowManager();
            windowManager.removeView(floatWindowView);
            floatWindowView = null;
        }
    }

    /**
     * 获取WindowManager
     ** @return WindowManager
     */
    private WindowManager getWindowManager() {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }

}
