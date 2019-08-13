package com.demo.customview.music;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * created by 姚明亮
 * Time：2019/8/12 15:53
 */
public class MusicRow implements Comparable<MusicRow> {

    /**
     * 开始时间
     */
    private String timeStr;

    /**
     * 开始时间 毫米数
     */
    private int time;

    /**
     * 歌词内容
     */
    private String content;

    /**
     * 该行歌词显示的总时间
     */
    private int totalTime;

    public MusicRow(String timeStr, int time, String content) {
        super();
        this.timeStr = timeStr;
        this.time = time;
        this.content = content;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public static List<MusicRow> createRows(String lyricLine){
        if (lyricLine==null||!lyricLine.startsWith("[")||lyricLine.indexOf("]")!=9){
            return null;
        }
        //最后一个"]"
        int lastIndexOfRightBracket = lyricLine.lastIndexOf("]");
        //歌词内容
        String content = lyricLine.substring(lastIndexOfRightBracket+1,lyricLine.length());
        //截取出歌词，并将"["和"]"替换为"-"  [offset:0]
        Log.e("yml=", "lyricLine="+lyricLine );
        // -03:33.02--00:36.37-
        String times = lyricLine.substring(0,lastIndexOfRightBracket+1).replace("[","-").replace("]","-");
        String[] timesArray = times.split("-");
        List<MusicRow> musicRows = new ArrayList<>();
        for (String tem:timesArray){
            if (TextUtils.isEmpty(tem.trim())){
                continue;
            }
            try {
                MusicRow musicRow = new MusicRow(tem,formatTime(tem),content);
                musicRows.add(musicRow);
            }catch (Exception e){
                Log.e("yml", "err"+e.getMessage() );
            }
        }
        return musicRows;
    }

    /**
     * 把歌词时间转换为毫秒值  如 将00:10.00  转为10000
     */
    private static int formatTime(String tem) {
        tem = tem.replace('.', ':');
        String[] times = tem.split(":");

        return Integer.parseInt(times[0]) * 60 * 1000
                + Integer.parseInt(times[1]) * 1000
                + Integer.parseInt(times[2]);
    }


    /**
     * @param musicRow
     * @return
     */

    @Override
    public int compareTo(MusicRow musicRow) {
        return this.time-musicRow.time;
    }

    @Override
    public String toString() {
        return "MusicRow{" +
                "timeStr='" + timeStr + '\'' +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", totalTime=" + totalTime +
                '}';
    }
}
