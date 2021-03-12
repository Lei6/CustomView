package com.demo.customview.picture_processing


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.customview.R
import kotlinx.android.synthetic.main.activity_picture.*

/**
 * Author by YML, Date on 2021/3/9.
 * PS: Not easy to write code, please indicate.
 */
class PictureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        btn_save.setOnClickListener {
            val windowDisplayMetrics = SystemUtils.getWindowDisplayMetrics(this)
            val width = windowDisplayMetrics!!.widthPixels
            val height = windowDisplayMetrics!!.heightPixels - SystemUtils.getStatusBarHeight(this) - SystemUtils.getActionBarHeight(this) - 50
            val generateImageFromView = SystemUtils.generateImageFromView(this.qrcodeView, width, height)
            val saveImageToGallery = SystemUtils.saveImageToGallery(this, generateImageFromView!!)
//            generateImageFromView.recycle()

            if (saveImageToGallery){
                Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"保存失败",Toast.LENGTH_LONG).show()
            }
        }


    }

}