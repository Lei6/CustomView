package com.demo.customview.popupWindow

import android.content.Context
import android.graphics.*
import android.view.View
import com.demo.customview.CommonUtils

/**
 * Author by YML, Date on 2021/8/26.
 * PS: Not easy to write code, please indicate.
 */
class PopupWindowBackground(context: Context?) : View(context) {

    var mPaint: Paint = Paint()
    var mPath: Path = Path()
    var mCenterPosition: IntArray? = null
    var mContentPosition: IntArray? = null
    var mCenterViewHeight = 0
    var mPopupViewWidth = 0
    var mPopupViewHeight = 0
    var mTriangleWidth = 0
    var mTriangleHeight = 0
    var mDrawMargin = 0f
    var mRadius = 10f
    var isShowDown = true

    init {
        mPaint.color = Color.GRAY
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL
    }

    fun convertViewToBitmap():Bitmap{
        val bitmap = Bitmap.createBitmap(mPopupViewWidth,
            (mPopupViewHeight + mCenterViewHeight + mTriangleHeight + 2 * mDrawMargin).toInt(),Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        draw(canvas)
        return bitmap
    }

    fun setShowDirection(showDown: Boolean) {
        isShowDown = showDown
    }

    fun setContentPosition(contentPosition: IntArray) {
        mContentPosition = contentPosition
    }

    fun setViewWidth(viewWidth: Int) {
        mPopupViewWidth = viewWidth
    }

    fun setViewHeight(viewHeight: Int) {
        mPopupViewHeight = viewHeight
    }

    fun setPosCenterPosition(posCenterPosition: IntArray) {
        mCenterPosition = posCenterPosition
    }

    fun setPosViewHeight(posViewHeight: Int) {
        mCenterViewHeight = posViewHeight
    }

    fun setTranWidth(tranWidth: Int) {
        mTriangleWidth = tranWidth
    }

    fun setTranHeight(tranHeight: Int) {
        mTriangleHeight = tranHeight
    }


    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        val triangleX: Int = mCenterPosition!![0] - mContentPosition!![0]
        val halfTriangleWidth = mTriangleWidth / 2
        if (isShowDown) {
            canvas?.let {
                it.drawRoundRect(
                    RectF(
                        mDrawMargin,
                        mDrawMargin + mTriangleHeight,
                        it.width - mDrawMargin,
                        it.height - mDrawMargin - CommonUtils.dip2px(
                            context, 10f
                        )
                    ), mRadius, mRadius, mPaint
                )
                mPath.moveTo(triangleX.toFloat(), mDrawMargin)
                mPath.lineTo(
                    (triangleX + halfTriangleWidth).toFloat(),
                    (mDrawMargin + mTriangleHeight * 2)
                )
                mPath.lineTo(
                    (triangleX - halfTriangleWidth).toFloat(),
                    (mDrawMargin + mTriangleHeight * 2)
                )
                mPath.close()
                it.drawPath(mPath, mPaint)
            }
        } else {
            canvas?.let {
                it.drawRoundRect(
                    RectF(
                        mDrawMargin,
                        mDrawMargin,
                        it.width - mDrawMargin,
                        it.height - mDrawMargin - mTriangleHeight
                    ),
                    mRadius, mRadius, mPaint
                )

                mPath.moveTo(triangleX.toFloat(), it.height - mDrawMargin)
                mPath.lineTo(
                    (triangleX + halfTriangleWidth).toFloat(),
                    (it.height - mDrawMargin - mTriangleHeight * 2)
                )
                mPath.lineTo(
                    (triangleX - halfTriangleWidth).toFloat(),
                    (it.height - mDrawMargin - mTriangleHeight * 2)
                )
                mPath.close()
                it.drawPath(mPath, mPaint)
            }
        }
    }
}