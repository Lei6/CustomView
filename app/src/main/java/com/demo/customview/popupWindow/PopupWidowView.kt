package com.demo.customview.popupWindow

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.customview.CommonUtils
import com.demo.customview.R

/**
 * Author by YML, Date on 2021/8/26.
 * PS: Not easy to write code, please indicate.
 */
class PopupWidowView(context: Context?) : PopupWindow(context) {
    private var mContentView: View? = null
    private var mAdapter:PopupWindowAdapter? = null
    private val mTriangleHeight = 30
    private val mTriangleWidth = 40
    private var mContext:Context?=null

    init {
        mContext = context
        mContentView = LayoutInflater.from(context).inflate(R.layout.popup_view, null)
        val listView = mContentView?.findViewById<RecyclerView>(R.id.select_item_listView)
        mAdapter = PopupWindowAdapter(context,null,R.layout.popup_item_view)
        listView?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = mAdapter
        }
        contentView = mContentView
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        isOutsideTouchable = true
    }

    fun setData(data: ArrayList<ButtonData>) {
        mAdapter?.setData(data)
    }

    fun showAtLocation(parentVIew:View,positionView:View){
        val popupWindowPos = calculatePopupWindowPos(mContentView!!, positionView, mTriangleHeight, width)
        val positionViewCenterPos = getPositionViewCenterPos(positionView)
       val mPopupBackView = PopupWindowBackground(mContext)
        mPopupBackView.setContentPosition(popupWindowPos!!)
        mPopupBackView.setPosCenterPosition(positionViewCenterPos!!)
        mPopupBackView.setPosViewHeight(positionView.measuredHeight)
        mPopupBackView.setViewWidth(width) //注意这里传入的参数为popop的宽度
        mPopupBackView.setViewHeight(mContentView!!.measuredHeight)
        mPopupBackView.setShowDirection(isShowDown(mContentView!!, positionView, mTriangleHeight))
        mPopupBackView.setTranWidth(mTriangleWidth)
        mPopupBackView.setTranHeight(mTriangleHeight)
        val bitmap: Bitmap = mPopupBackView.convertViewToBitmap()
        val drawable: Drawable = BitmapDrawable(null, bitmap)
        update()
        setBackgroundDrawable(drawable)
        showAtLocation(parentVIew, Gravity.TOP or Gravity.START, popupWindowPos[0], popupWindowPos[1])
    }

    private fun isShowDown(contentView: View, positionView: View, triangleHeight: Int): Boolean {
        val positionPos = IntArray(2)
        //获取到哪个位置view在屏幕中 的位置
        positionView.getLocationOnScreen(positionPos)
        val screenHeight: Int = CommonUtils.getScreenHeight(positionView.context)
        val posViewHeight = positionView.measuredHeight
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val contentViewHeight = contentView.measuredHeight
        return screenHeight - positionPos[1] - posViewHeight > contentViewHeight + triangleHeight
    }

    /**
     * 计算PopupWindow位置
     */
    private fun calculatePopupWindowPos(
        contentView: View,
        positionView: View,
        triangleHeight: Int,
        viewWidth: Int
    ): IntArray? {
        val contentPos = IntArray(2)
        val positionPos = IntArray(2)
        //获取到哪个位置view在屏幕中 的位置
        positionView.getLocationOnScreen(positionPos)
        val screenWidth: Int = CommonUtils.getScreenWidth(positionView.context)
        val posViewHeight = positionView.measuredHeight
        val posViewWidth = positionView.measuredWidth
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val contentViewHeight = contentView.measuredHeight
        val isShowDown: Boolean = isShowDown(contentView, positionView, triangleHeight)
        if (isShowDown) {
            when {
                viewWidth <= posViewWidth -> {
                    contentPos[0] = positionPos[0] + (posViewWidth - viewWidth) / 2
                }
                positionPos[0] < viewWidth -> {
                    contentPos[0] = positionPos[0] / 2
                }
                screenWidth - positionPos[0] > viewWidth -> {
                    contentPos[0] = positionPos[0] - Math.abs(viewWidth - posViewWidth) / 2
                }
                else -> {
                    //表示在最右边，给右边留下posViewWidth/2的空白区域
                    contentPos[0] = screenWidth - viewWidth - posViewWidth / 2
                }
            }
            contentPos[1] = positionPos[1] + posViewHeight + triangleHeight
        } else {
            //如果positionView的位置在最左边，且宽度没有内容那么宽，那么就取一半，
            //为什么不取0，那是因为想给显示的popup左边留一部分控件
            when {
                positionPos[0] < viewWidth -> {
                    contentPos[0] = positionPos[0] / 2
                }
                screenWidth - positionPos[0] > viewWidth -> {
                    contentPos[0] = positionPos[0] - Math.abs(viewWidth - posViewWidth) / 2
                }
                else -> {
                    //表示在最右边，给右边留下posViewWidth/2的空白区域
                    contentPos[0] = screenWidth - viewWidth - posViewWidth / 2
                }
            }
            contentPos[1] = positionPos[1] - contentViewHeight - triangleHeight
        }
        return contentPos
    }

    /**
     * 计算点击View的位置
     */
    open fun getPositionViewCenterPos(positionView: View): IntArray? {
        val positionPos = IntArray(2)
        //获取到哪个位置view在屏幕中 的位置
        positionView.getLocationOnScreen(positionPos)
        val posViewHeight = positionView.measuredHeight
        val posViewWidth = positionView.measuredWidth
        positionPos[0] = positionPos[0] + posViewWidth / 2
        positionPos[1] = positionPos[1] + posViewHeight / 2
        return positionPos
    }

}