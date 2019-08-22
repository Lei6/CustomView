package com.demo.customview.slidingdrawer;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.demo.customview.R;
import com.demo.customview.adapter.CommonAdapter;
import com.demo.customview.adapter.ViewHolder;

import java.util.ArrayList;

/**
 * created by 姚明亮
 * Time：2019/8/22 17:42
 */
public class PoiAdapter extends CommonAdapter<PoiBean> {
    public PoiAdapter(Context mContext, ArrayList<PoiBean> mData, int mLayoutId) {
        super(mContext, mData, mLayoutId);
    }

    @Override
    public void bindData(ViewHolder holder, final PoiBean poiBean, int position) {
        holder.setText(R.id.tv_name, poiBean.title);
        holder.setText(R.id.tv_address, "..." + " | " + poiBean.address);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "" + poiBean.title, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
