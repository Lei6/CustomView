package com.demo.customview.sort;

import android.content.Context;
import android.view.View;

import com.demo.customview.R;
import com.demo.customview.adapter.CommonAdapter;
import com.demo.customview.adapter.ViewHolder;

import java.util.ArrayList;

/**
 * created by 姚明亮
 * Time：2019/8/20 14:12
 */
public class SortAdapter extends CommonAdapter<SortBean> {

    public SortAdapter(Context mContext, ArrayList<SortBean> mData, int mLayoutId) {
        super(mContext, mData, mLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, SortBean sortBean, int position) {
        if (sortBean.isLetter){
            holder.setViewVisibility(R.id.llyt_sort, View.VISIBLE);
            holder.setText(R.id.tv_letter,sortBean.letter);
        }else {
            holder.setViewVisibility(R.id.llyt_sort,View.GONE);
        }
        holder.setText(R.id.tv_content,sortBean.content);
    }
}
