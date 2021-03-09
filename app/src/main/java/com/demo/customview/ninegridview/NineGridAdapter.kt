package com.demo.customview.ninegridview

import android.content.Context
import com.demo.customview.R
import com.demo.customview.adapter.CommonAdapter
import com.demo.customview.adapter.ViewHolder

/**
 * Author by YML, Date on 2021/3/9.
 * PS: Not easy to write code, please indicate.
 */
class NineGridAdapter(mContext:Context,data:List<List<ImageBean>>,layoutId:Int):CommonAdapter<List<ImageBean>>(mContext,data,layoutId) {

    override fun bindData(holder: ViewHolder?, t: List<ImageBean>, position: Int) {
        holder?.setText(R.id.tv_sequence,"序列$position")
        val nineGridlayout = holder?.getView(R.id.nine_grid_a) as NineGridlayout?
        nineGridlayout?.setImagesData(t)

    }
}