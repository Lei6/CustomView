package com.demo.customview.sort;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import com.demo.customview.R;
import com.demo.customview.adapter.CommonAdapter;
import com.demo.customview.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * created by 姚明亮
 * Time：2019/8/20 14:12
 */
public class SortAdapter extends CommonAdapter<SortBean> implements Filterable {

    private Filters filters;

    public SortAdapter(Context mContext, List<SortBean> mData, int mLayoutId) {
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,ContactInfoActivity.class));
            }
        });
    }

    @Override
    public Filter getFilter() {
        if (filters==null){
            filters = new Filters(mData);
        }
        return filters;
    }

    private class Filters extends Filter{

        private List<SortBean> orighinal;

        public Filters(List<SortBean> orighinal) {
            this.orighinal = orighinal;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            if (charSequence==null||charSequence.length()==0){
                results.values = orighinal;
                results.count = orighinal.size();
            }else {
                List<SortBean> mList = new ArrayList<>();
                for (SortBean p : orighinal){
                    if (p.telPhone.toUpperCase().startsWith(charSequence.toString().toUpperCase())||
                    p.content.toUpperCase().startsWith(charSequence.toString().toUpperCase())){
                        mList.add(p);
                    }
                }
                results.values = mList;
                results.count = mList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mData = (List<SortBean>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
