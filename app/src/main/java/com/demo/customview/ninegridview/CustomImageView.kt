package com.demo.customview.ninegridview

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.MotionEvent
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Author by YML, Date on 2021/2/26.
 * PS: Not easy to write code, please indicate.
 */
class CustomImageView(mContext: Context) : ImageView(mContext) {

    private var isAttachedTowindow: Boolean = false
    private var url: Int = 0

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (drawable != null) {
                    drawable.mutate().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
                }
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                if (drawable != null) {
                    drawable.mutate().clearColorFilter()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onAttachedToWindow() {
        isAttachedTowindow = true
        setImageUrl(url)
        super.onAttachedToWindow()
    }

    fun setImageUrl(url: Int?) {
        if (url!=null) {
            this.url = url
            if (isAttachedTowindow) {
                Glide.with(context)
                        .load(url)
                        .placeholder(ColorDrawable(Color.parseColor("#fafafa")))
                        .into(this)
            }
        }
    }

    override fun onDetachedFromWindow() {
        isAttachedTowindow = false
        setImageBitmap(null)
        super.onDetachedFromWindow()
    }
}