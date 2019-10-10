package com.demo.customview.logisticstraces;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.customview.CommonUtils;
import com.demo.customview.R;
import com.demo.customview.adapter.CommonAdapter;
import com.demo.customview.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * created by 姚明亮
 * Time：2019/9/17 09:29
 */
public class LogisticsAdapter extends CommonAdapter<TracesBean> {

    private static final int TYPE_TOP = 0x0000;
    private static final int TYPE_NORMAL = 0x0001;

    public LogisticsAdapter(Context mContext, ArrayList<TracesBean> mData, int mLayoutId) {
        super(mContext, mData, mLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, TracesBean tracesBean, int position) {

        if (position == 0) {
            // 第一行头的竖线不显示
            holder.setViewVisibility(R.id.tvTopLine, View.INVISIBLE);
            // 字体颜色加深
            holder.setTextColor(R.id.tvAcceptTime, 0xff555555);
//            holder.setTextColor(R.id.tvAcceptStation, mContext.getResources().getColor(R.color.colorAccent));
            holder.setBackground(R.id.tvDot, R.mipmap.ic_wuliu);

            TextView view = (TextView) holder.getView(R.id.tvDot);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = (int) mContext.getResources().getDimension(R.dimen.dp_22);
            layoutParams.height = (int) mContext.getResources().getDimension(R.dimen.dp_22);
            view.setLayoutParams(layoutParams);


//            TextView textView = (TextView) holder.getView(R.id.tvAcceptStation);
//            textView.setText(spannableString);

            try {
                holder.setText(R.id.tvAcceptStation, CommonUtils.getKeyWordSpan(mContext,R.color.colorFC4B62,tracesBean.getAcceptStation(),"0"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.setText(R.id.tvAcceptTime, tracesBean.getAcceptTime());

        } else {
            holder.setViewVisibility(R.id.tvTopLine, View.VISIBLE);
            holder.setTextColor(R.id.tvAcceptTime, 0xff999999);
            holder.setTextColor(R.id.tvAcceptStation, 0xff999999);
            holder.setBackground(R.id.tvDot, R.drawable.timelline_dot_normal);

            holder.setText(R.id.tvAcceptStation, tracesBean.getAcceptStation());
            holder.setText(R.id.tvAcceptTime, tracesBean.getAcceptTime());
        }

    }

}
