package com.demo.customview.picture_scrolling

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.annotation.RequiresApi
import com.demo.customview.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ScrollingImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val BITMAP_LOADER: AndroidScrollingImageView =
        object : AndroidScrollingImageView {
            override fun loadDrawable(context: Context, resourceId: Int): Bitmap {
                val drawable = context.resources.getDrawable(resourceId, context.theme)
                if (drawable is BitmapDrawable) {
                    return drawable.bitmap
                }

                // Render any other kind of drawable to a bitmap
                val bitmap = Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
                return bitmap
            }
        }

    val paint: Paint? = null

    var bitmaps: List<Bitmap>? = null

    /** Pixels per second  */
    var speed = 0.0
    lateinit var scene: IntArray
    var arrayIndex = 0
    var maxBitmapHeight = 0

    val clipBounds_ = Rect()
    var offset = 0f

    val NANOS_PER_SECOND = 1e9

    /** Moment when the last call to onDraw() started  */
    var lastFrameInstant: Long = -1
    var frameTimeNanos: Long = -1

    var isStarted = false

    init{
        val ta = context!!.obtainStyledAttributes(attrs, R.styleable.ScrollingImageView, 0, 0)
        var initialState = 0
        try {
            initialState = ta.getInt(R.styleable.ScrollingImageView_initialState, 0)
            speed = ta.getDimension(R.styleable.ScrollingImageView_speed, 60f).toDouble()
            val sceneLength = ta.getInt(R.styleable.ScrollingImageView_sceneLength, 1000)
            val randomnessResourceId =
                ta.getResourceId(R.styleable.ScrollingImageView_randomness, 0)
            // When true, randomness is ignored and bitmaps are loaded in the order as they appear in the src array */
            val contiguous = ta.getBoolean(R.styleable.ScrollingImageView_contiguous, false)
            var randomness = IntArray(0)
            if (randomnessResourceId > 0) {
                randomness = resources.getIntArray(randomnessResourceId)
            }
            val type =
                if (isInEditMode) TypedValue.TYPE_STRING else ta.peekValue(R.styleable.ScrollingImageView_source).type
            if (type == TypedValue.TYPE_REFERENCE) {
                val resourceId = ta.getResourceId(R.styleable.ScrollingImageView_source, 0)
                val typedArray = resources.obtainTypedArray(resourceId)
                try {
                    var bitmapsSize = 0
                    for (r in randomness) {
                        bitmapsSize += r
                    }
                    bitmaps = ArrayList(typedArray.length().coerceAtLeast(bitmapsSize))
                    for (i in 0 until typedArray.length()) {
                        var multiplier = 1
                        if (randomness.isNotEmpty() && i < randomness.size) {
                            multiplier = 1.coerceAtLeast(randomness[i])
                        }
                        val bitmap: Bitmap = BITMAP_LOADER.loadDrawable(
                            getContext(),
                            typedArray.getResourceId(i, 0)
                        )
                        for (m in 0 until multiplier) {
                            (bitmaps as ArrayList).add(bitmap)
                        }
                        maxBitmapHeight = bitmap.height.coerceAtLeast(maxBitmapHeight)
                    }
                    val random = Random()
                    scene = IntArray(sceneLength)
                    for (i in scene.indices) {
                        if (contiguous) {
                            scene[i] = i % (bitmaps as ArrayList).size
                        } else {
                            scene[i] = random.nextInt((bitmaps as ArrayList).size)
                        }
                    }
                } finally {
                    typedArray.recycle()
                }
            } else if (type == TypedValue.TYPE_STRING) {
                val bitmap: Bitmap = BITMAP_LOADER.loadDrawable(
                    getContext(),
                    ta.getResourceId(R.styleable.ScrollingImageView_source, 0)
                )
                if (bitmap != null) {
                    bitmaps = listOf(bitmap)
                    scene = intArrayOf(0)
                    maxBitmapHeight = bitmaps!![0].height
                } else {
                    bitmaps = emptyList()
                }
            }
        } finally {
            ta.recycle()
        }
        if (initialState == 0) {
            start()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), maxBitmapHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        if (!isInEditMode) {
            if (lastFrameInstant == -1L) {
                lastFrameInstant = System.nanoTime()
            }
            frameTimeNanos = System.nanoTime() - lastFrameInstant
            lastFrameInstant = System.nanoTime()
            super.onDraw(canvas)
            if (canvas == null || bitmaps!!.isEmpty()) {
                return
            }
            canvas.getClipBounds(clipBounds_)
            while (offset <= -getBitmap(arrayIndex).width) {
                offset += getBitmap(arrayIndex).width.toFloat()
                arrayIndex = (arrayIndex + 1) % scene.size
            }
            var left = offset
            var i = 0
            while (left < clipBounds_.width()) {
                val bitmap: Bitmap = getBitmap((arrayIndex + i) % scene.size)
                val width = bitmap.width
                canvas.drawBitmap(bitmap, getBitmapLeft(width.toFloat(), left), 0f, paint)
                left += width.toFloat()
                i++
            }
            if (isStarted && speed != 0.0) {
                offset -= (abs(speed) / NANOS_PER_SECOND * frameTimeNanos).toFloat()
                postInvalidateOnAnimation()
            }
        }
    }

    private fun getBitmap(sceneIndex: Int): Bitmap {
        return bitmaps!![scene[sceneIndex]]
    }

    private fun getBitmapLeft(layerWidth: Float, left: Float): Float {
        return if (speed < 0) {
            clipBounds_.width() - layerWidth - left
        } else {
            left
        }
    }

    /**
     * Start the animation
     */
    fun start() {
        if (!isStarted) {
            isStarted = true
            lastFrameInstant = -1
            postInvalidateOnAnimation()
        }
    }

    /**
     * Stop the animation
     */
    fun stop() {
        if (isStarted) {
            isStarted = false
            lastFrameInstant = -1
            invalidate()
        }
    }
}