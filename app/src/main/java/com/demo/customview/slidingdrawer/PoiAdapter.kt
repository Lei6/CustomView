package com.demo.customview.slidingdrawer

import android.content.Context
import android.widget.Toast
import com.demo.customview.R
import com.demo.customview.adapter.CommonAdapter
import com.demo.customview.adapter.ViewHolder
import java.util.*

/**
 * Author by YML, Date on 2021/3/9.
 * PS: Not easy to write code, please indicate.
 */
class PoiAdapter(mContext:Context,mData:ArrayList<PoiBean>,mLayoutId:Int):CommonAdapter<PoiBean>(mContext, mData, mLayoutId) {
    override fun bindData(holder: ViewHolder?, poiBean: PoiBean?, position: Int) {
        holder!!.setText(R.id.tv_name, poiBean?.title)
        holder!!.setText(R.id.tv_address, "..." + " | " + poiBean?.address)
        holder!!.itemView.setOnClickListener { Toast.makeText(mContext, "" + poiBean?.title, Toast.LENGTH_SHORT).show() }
    }
}