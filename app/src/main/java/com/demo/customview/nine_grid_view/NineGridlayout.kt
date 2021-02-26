package com.demo.customview.nine_grid_view

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.ImageView
import com.demo.customview.CommonUtils

/**
 * Author by YML, Date on 2021/2/26.
 * PS: Not easy to write code, please indicate.
 */
class NineGridlayout(mContext:Context) : ViewGroup(mContext) {

    private val gap = 5
    private var mRows = 0  //行
    private var mColumns = 0  //列
    private var totalWidth = 0
    private var listData:List<ImageBean>? = null

    init {
         totalWidth = CommonUtils.getScreenWidth(mContext) - CommonUtils.dip2px(mContext, 60f)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

    //设置数据
    fun setImagesData(list:List<ImageBean>){
        if (list.isEmpty()){
            return
        }
        //根据图片个数确定行列数量
        initNineGridView(list.size)

        if (listData==null){
            var i = 0;
            while (i<list.size){
                val imageView = createImageView()
                addView(imageView,generateDefaultLayoutParams())
                i++
            }
        }else{
            val oldViewCount = listData?.size
            val newViewCount = list.size
            if (oldViewCount!=null) {
                if (oldViewCount > newViewCount) {
                    removeViews(newViewCount-1,oldViewCount-newViewCount)
                }else if (oldViewCount<newViewCount){
                    for (i in 0 until newViewCount - oldViewCount) {
                        val iv: CustomImageView = createImageView()
                        addView(iv, generateDefaultLayoutParams())
                    }
                }
            }
        }
        listData = list
        layoutChildrenView()
    }

    private fun layoutChildrenView() {
        val childrenCount = listData?.size
        val singleWidth = (totalWidth - gap)
    }

    /**
     * 根据图片个数确定行列数量
     * 对应关系如下
     * num	row	column
     * 1	   1	1
     * 2	   1	2
     * 3	   1	3
     * 4	   2	2
     * 5	   2	3
     * 6	   2	3
     * 7	   3	3
     * 8	   3	3
     * 9	   3	3
     *
     * @param length
     */
    private fun initNineGridView(size: Int) {
        if (size<=0){
            mRows = 1
            mColumns = size
        }else if (size<=6){
            mRows = 2
            mColumns = 3
            if (size==4){
                mColumns = 2
            }
        }else{
            mRows = 3
            mColumns = 3
        }
    }

    private fun createImageView():CustomImageView{
        val iv = CustomImageView(context)
        iv.scaleType = ImageView.ScaleType.CENTER_CROP
        iv.setBackgroundColor(Color.parseColor("#f5f5f5"))
        iv.setOnClickListener {

        }
        return iv
    }
}