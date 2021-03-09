package com.demo.customview.ninegridview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import com.demo.customview.CommonUtils

/**
 * Author by YML, Date on 2021/2/26.
 * PS: Not easy to write code, please indicate.
 */
class NineGridlayout@JvmOverloads constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int = 0): ViewGroup(context,attrs) {

    /**
     *
     * 图片之间的间隔
     */
    private var gap = 5
    private var columns //
            = 0
    private var rows //
            = 0
    private var listData: List<*>? = null
    private var totalWidth = 0

    init {
        totalWidth = CommonUtils.getScreenWidth(context) - CommonUtils.dip2px(context, 60f)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {}

    private fun layoutChildrenView() {
        val childrenCount = listData!!.size
        val singleWidth = (totalWidth - gap * (3 - 1)) / 3

        //根据子view数量确定高度
        val params = layoutParams
        params.height = singleWidth * rows + gap * (rows - 1)
        layoutParams = params
        for (i in 0 until childrenCount) {
            val childrenView = getChildAt(i) as CustomImageView
            childrenView.setImageUrl((listData!![i] as ImageBean).url)
            val position = findPosition(i)
            val left = (singleWidth + gap) * position[1]
            val top = (singleWidth + gap) * position[0]
            val right = left + singleWidth
            val bottom = top + singleWidth
            childrenView.layout(left, top, right, bottom)
        }
    }


    private fun findPosition(childNum: Int): IntArray {
        val position = IntArray(2)
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                if (i * columns + j == childNum) {
                    position[0] = i //行
                    position[1] = j //列
                    break
                }
            }
        }
        return position
    }

    fun getGap(): Int {
        return gap
    }

    fun setGap(gap: Int) {
        this.gap = gap
    }


    fun setImagesData(lists: List<ImageBean?>?) {
        if (lists == null || lists.isEmpty()) {
            return
        }
        //初始化布局
        generateChildrenLayout(lists.size)
        //这里做一个重用view的处理
        if (listData == null) {
            var i = 0
            while (i < lists.size) {
                val iv = generateImageView()
                addView(iv, generateDefaultLayoutParams())
                i++
            }
        } else {
            val oldViewCount = listData!!.size
            val newViewCount = lists.size
            if (oldViewCount > newViewCount) {
                removeViews(newViewCount - 1, oldViewCount - newViewCount)
            } else if (oldViewCount < newViewCount) {
                for (i in 0 until newViewCount - oldViewCount) {
                    val iv = generateImageView()
                    addView(iv, generateDefaultLayoutParams())
                }
            }
        }
        listData = lists
        layoutChildrenView()
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
    private fun generateChildrenLayout(length: Int) {
        if (length <= 3) {
            rows = 1
            columns = length
        } else if (length <= 6) {
            rows = 2
            columns = 3
            if (length == 4) {
                columns = 2
            }
        } else {
            rows = 3
            columns = 3
        }
    }

    private fun generateImageView(): CustomImageView {
        val iv = CustomImageView(context)
        iv.scaleType = ImageView.ScaleType.CENTER_CROP
        iv.setOnClickListener { }
        iv.setBackgroundColor(Color.parseColor("#f5f5f5"))
        return iv
    }
}