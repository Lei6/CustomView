package com.demo.customview.adapter

/**
 * Author by YML, Date on 2021/3/9.
 * PS: Not easy to write code, please indicate.
 */
interface MultipleType<T> {
    fun getLayoutId(item: T?, position: Int): Int
}