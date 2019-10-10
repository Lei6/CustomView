package com.demo.customview;

import android.content.Context;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * created by 姚明亮
 * Time：2019/8/12 16:31
 */
public class CommonUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) (dpValue * (metrics.densityDpi / 160f));
    }

    /**
     * 获取字体高度
     */
    public static float getTextHeight(Paint p) {
        Paint.FontMetrics fm = p.getFontMetrics();
        return (float) ((Math.ceil(fm.descent - fm.top) + 2) / 2);
    }


    public static String getNumber(String content) {
        Pattern p = Pattern.compile("\\d{3}-\\d{8}|\\d{4}-\\d{7}|\\d{11}");
        Matcher matcher = p.matcher(content);
        if (matcher.find()) {
            String n = matcher.group(0);
            return n;
        }
        return null;
    }

    /**
     * 关键词变色处理
     * @param str
     * @param patterStr 需要变色的关键词 或者 正则表达式
     * @return
     */
    public static SpannableString getKeyWordSpan(Context context,int color, String str, String patterStr) throws Exception {
        String number = getNumber(str);
        SpannableString spannableString = new SpannableString(str);
        Pattern patten = Pattern.compile(number, Pattern.CASE_INSENSITIVE);
        dealPattern(context.getResources().getColor(color), spannableString, patten, 0);
        return spannableString;
    }


    /**
     * 对spanableString进行正则判断，如果符合要求，则将内容变色
     * @param color
     * @param spannableString
     * @param patten
     * @param start
     * @throws Exception
     */
    private static void dealPattern(int color, SpannableString spannableString, Pattern patten, int start) throws Exception {
        Matcher matcher = patten.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            // 返回第一个字符的索引的文本匹配整个正则表达式,ture 则继续递归
            if (matcher.start() < start) {
                continue;
            }
            // 计算该内容的长度，也就是要替换的字符串的长度
            int end = matcher.start() + key.length();
            //设置前景色span
            spannableString.setSpan(new ForegroundColorSpan(color), matcher.start(), end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View view) {

                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            },matcher.start(),end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (end < spannableString.length()) {
                // 如果整个字符串还未验证完，则继续。。
                dealPattern(color, spannableString, patten, end);
            }
            break;
        }
    }
}
