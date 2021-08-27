package com.demo.customview.popupWindow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.customview.CommonUtils
import com.demo.customview.R
import kotlinx.android.synthetic.main.activity_popup_window.*

/**
 * Author by YML, Date on 2021/8/26.
 * PS: Not easy to write code, please indicate.
 */
class PopupWindowActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_window)

        val popupWindow = PopupWidowView(this)
        val datas = ArrayList<ButtonData>()
        for (i in 0 until 3){
            val buttonData = ButtonData(R.mipmap.ic_launcher,"选项${i}")
            datas.add(buttonData)
        }

        //必须设置宽度
        popupWindow.width = CommonUtils.dip2px(this, 160f)
        popupWindow.setData(datas)
        image_one.setOnClickListener {
            popupWindow.showAtLocation(window.decorView,image_one)
        }
        image_two.setOnClickListener {
            popupWindow.showAtLocation(window.decorView,image_two)
        }
        image_three.setOnClickListener {
            popupWindow.showAtLocation(window.decorView,image_three)
        }
        image_four.setOnClickListener {
            popupWindow.showAtLocation(window.decorView,image_four)
        }
        image_five.setOnClickListener {
            popupWindow.showAtLocation(window.decorView,image_five)
        }
    }

}