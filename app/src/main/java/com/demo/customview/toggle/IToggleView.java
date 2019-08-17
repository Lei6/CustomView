package com.demo.customview.toggle;

/**
 * created by 姚明亮
 * Time：2019/8/17 16:35
 */
public interface IToggleView {
    void toggle();

    void setOpen(boolean open);

    boolean isOpen();

    void setOnToggleListener(OnToggleListener listener);

    interface OnToggleListener {
        void onToggle(boolean isOpen);
    }
}
