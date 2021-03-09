package com.demo.customview.sort

import android.text.TextUtils
import java.util.*

/**
 * Author by YML, Date on 2021/3/9.
 * PS: Not easy to write code, please indicate.
 */
class PinyinComparator: Comparator<SortBean> {
    override fun compare(sortBean: SortBean, t1: SortBean): Int {
        return when {
            TextUtils.equals(sortBean.letter, "#") -> {
                1
            }
            TextUtils.equals(t1.letter, "#") -> {
                -1
            }
            else -> {
                sortBean.pinyin.compareTo(t1.pinyin)
            }
        }
    }
}