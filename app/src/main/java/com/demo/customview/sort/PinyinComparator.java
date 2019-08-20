package com.demo.customview.sort;

import android.text.TextUtils;

import java.util.Comparator;

/**
 * created by 姚明亮
 * Time：2019/8/20 14:04
 */
public class PinyinComparator implements Comparator<SortBean> {
    @Override
    public int compare(SortBean sortBean, SortBean t1) {
        if (TextUtils.equals(sortBean.letter, "#")) {
            return 1;
        } else if (TextUtils.equals(t1.letter, "#")) {
            return -1;
        } else {
            return sortBean.pinyin.compareTo(t1.pinyin);
        }
    }
}
