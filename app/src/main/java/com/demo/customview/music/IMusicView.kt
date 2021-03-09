package com.demo.customview.music

import android.content.Context

/**
 * Author by YML, Date on 2021/3/9.
 * PS: Not easy to write code, please indicate.
 */
interface IMusicView {
    /**
     * 初始化
     */
    fun init(context: Context?)

    /**
     * 设置数据
     */
    fun setData(musicRow: ArrayList<MusicRow>)


    /**
     * 指定时间
     */
    fun seekTo(progress: Int, fromUser: Boolean)

    /**
     * 设置歌词缩放比
     */
    fun setMusicScale(factor: Float)

    /**
     * 重置
     */
    fun reset()
}