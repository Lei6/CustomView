package com.demo.customview.picture_processing

import android.R
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * Author by YML, Date on 2021/3/12.
 * PS: Not easy to write code, please indicate.
 */
class SystemUtils {

    companion object{
        fun getStatusBarHeight(context: Context): Int {
            var result = 0
            val resourceId: Int = context.getResources().getIdentifier("status_bar_height", "dimen", "android")
            result = if (resourceId > 0) {
                context.getResources().getDimensionPixelSize(resourceId)
            } else {
                25;
            }
            return result
        }

        fun getWindowDisplayMetrics(context: Context): DisplayMetrics? {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics
        }

        fun getActionBarHeight(context: Context): Int {
            val attrs = intArrayOf(R.attr.actionBarSize)
            val values = context.theme.obtainStyledAttributes(attrs)
            var actionBarHeight = values.getDimensionPixelSize(0, 0)
            values.recycle()
            if (actionBarHeight <= 0) {
                actionBarHeight = 54
            }
            return actionBarHeight
        }

        fun generateImageFromView(view: View, width: Int, height: Int): Bitmap? {
            //获取cache通常会占用一定的内存，所以通常不需要的时候有必要对其进行清理，通过destroyDrawingCache或setDrawingCacheEnabled(false)实现
            // view.setDrawingCacheEnabled(true)为true是为开启缓存
            view.setDrawingCacheEnabled(true)
            //将缓存质量降低，减少内存
            view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH)
            view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY))
            view.layout(0, 0, width, height);

            //view转换成图片
            val image: Bitmap = Bitmap.createBitmap(view.getDrawingCache())
            //对cache进行清理
            view.destroyDrawingCache()
            return image
        }

        /**
         * 将图片保存到系统相册
         *
         * @param context
         * @param bmp
         * @return
         */
        fun saveImageToGallery(context: Context, bmp: Bitmap): Boolean {
            val galleryPath: String = Environment.getExternalStorageDirectory().absolutePath + File.separator + Environment.DIRECTORY_PICTURES
            val galleryDir = File(galleryPath)
            if (!galleryDir.exists()) {
                galleryDir.mkdirs()
            }
            val fileName = System.currentTimeMillis().toString() + ".jpg"
            val file = File(galleryPath, fileName)
            var fos: FileOutputStream? = null
            var isSuccess = false
            try {
                fos = FileOutputStream(file)
                isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()

                //保存图片后发送广播通知更新数据库
                val uri: Uri = Uri.fromFile(file)
                context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    if (fos != null) fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return isSuccess
        }
    }

}