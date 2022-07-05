package com.demo.customview.picture_scrolling

import android.content.Context
import android.graphics.Bitmap

interface AndroidScrollingImageView {
    fun loadDrawable(context: Context,resourceId:Int): Bitmap
}