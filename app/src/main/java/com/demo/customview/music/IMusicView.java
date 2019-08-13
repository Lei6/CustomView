package com.demo.customview.music;

import android.content.Context;

import java.util.List;

/**
 * created by 姚明亮
 * Time：2019/8/12 15:51
 */
interface IMusicView {

    /**
     * 初始化
     */
    void init(Context context);

    /**
     * 设置数据
     */

    void setData(List<MusicRow> musicRow);


    /**
     * 指定时间
     */
    void seekTo(int progress, boolean fromUser);

    /**
     * 设置歌词缩放比
     */
    void setMusicScale(float factor);

    /**
     * 重置
     */
    void reset();
}
