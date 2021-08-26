package com.demo.customview.popupWindow

import android.app.Activity
import android.content.Context
import com.demo.customview.BtnBean
import com.demo.customview.R
import com.demo.customview.adapter.CommonAdapter
import com.demo.customview.adapter.ViewHolder

/**
 * Author by YML, Date on 2021/8/26.
 * PS: Not easy to write code, please indicate.
 */
class PopupWindowAdapter(mContext: Context?, mData: ArrayList<ButtonData>?, mLayoutId: Int) : CommonAdapter<ButtonData>(mContext, mData, mLayoutId) {

    fun setData(data:ArrayList<ButtonData>){
        mData = data;
    }

    override fun bindData(holder: ViewHolder?, t: ButtonData?, position: Int) {
        holder?.setImageResource(R.id.image,mData[position].image)
        holder?.setText(R.id.item,mData[position].selectTitle)
    }
}