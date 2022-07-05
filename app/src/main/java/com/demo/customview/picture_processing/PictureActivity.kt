package com.demo.customview.picture_processing


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.customview.CommonUtils
import com.demo.customview.R
import com.demo.customview.ninegridview.ImageBean
import com.demo.customview.ninegridview.NineGridAdapter
import com.demo.customview.popupWindow.ButtonData
import com.demo.customview.popupWindow.PopupWidowView
import com.demo.customview.toggle.IToggleView.OnToggleListener
import kotlinx.android.synthetic.main.activity_folded_text.*
import kotlinx.android.synthetic.main.activity_nine_grid.*
import kotlinx.android.synthetic.main.activity_picture.*
import kotlinx.android.synthetic.main.activity_popup_window.*
import kotlinx.android.synthetic.main.activity_toggle_button.*
import java.text.FieldPosition

/**
 * Author by YML, Date on 2021/3/9.
 * PS: Not easy to write code, please indicate.
 */
class PictureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (intent.getIntExtra("index", 0)) {
            1, 8 -> {
                setContentView(R.layout.activity_toggle_button)
                initView(intent.getIntExtra("index", 0))
            }
            4 -> {
                setContentView(R.layout.activity_folded_text)
                initFoldedText()
            }
            5 -> {
                setContentView(R.layout.activity_nine_grid)
                initNineGrid()
            }
            6 -> {
                setContentView(R.layout.activity_picture)
                initPicture()
            }
            7 -> {
                setContentView(R.layout.activity_popup_window)
                initPopupWindow()
            }
            9 -> {
                setContentView(R.layout.activity_scrolling_image)
            }
        }
    }

    private fun initPopupWindow() {
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

    private fun initNineGrid() {
        val lists = ArrayList<List<ImageBean>>()
        val list1 = ArrayList<ImageBean>()
        list1.add(ImageBean(R.mipmap.haizw, 0, 0))
        lists.add(list1)

        val list2 = ArrayList<ImageBean>()
        list2.add(ImageBean(R.mipmap.haizw, 0, 0))
        list2.add(ImageBean(R.mipmap.haizw, 0, 0))
        lists.add(list2)

        val list3 = ArrayList<ImageBean>()
        list3.add(ImageBean(R.mipmap.haizw, 0, 0))
        list3.add(ImageBean(R.mipmap.haizw, 0, 0))
        list3.add(ImageBean(R.mipmap.haizw, 0, 0))
        lists.add(list3)

        val list4 = ArrayList<ImageBean>()
        list4.add(ImageBean(R.mipmap.haizw, 0, 0))
        list4.add(ImageBean(R.mipmap.haizw, 0, 0))
        list4.add(ImageBean(R.mipmap.haizw, 0, 0))
        list4.add(ImageBean(R.mipmap.haizw, 0, 0))
        lists.add(list4)

        val list5 = ArrayList<ImageBean>()
        list5.add(ImageBean(R.mipmap.haizw, 0, 0))
        list5.add(ImageBean(R.mipmap.haizw, 0, 0))
        list5.add(ImageBean(R.mipmap.haizw, 0, 0))
        list5.add(ImageBean(R.mipmap.haizw, 0, 0))
        list5.add(ImageBean(R.mipmap.haizw, 0, 0))
        lists.add(list5)

        val list6 = ArrayList<ImageBean>()
        list6.add(ImageBean(R.mipmap.haizw, 0, 0))
        list6.add(ImageBean(R.mipmap.haizw, 0, 0))
        list6.add(ImageBean(R.mipmap.haizw, 0, 0))
        list6.add(ImageBean(R.mipmap.haizw, 0, 0))
        list6.add(ImageBean(R.mipmap.haizw, 0, 0))
        list6.add(ImageBean(R.mipmap.haizw, 0, 0))
        lists.add(list6)

        val list7 = ArrayList<ImageBean>()
        list7.add(ImageBean(R.mipmap.haizw, 0, 0))
        list7.add(ImageBean(R.mipmap.haizw, 0, 0))
        list7.add(ImageBean(R.mipmap.haizw, 0, 0))
        list7.add(ImageBean(R.mipmap.haizw, 0, 0))
        list7.add(ImageBean(R.mipmap.haizw, 0, 0))
        list7.add(ImageBean(R.mipmap.haizw, 0, 0))
        list7.add(ImageBean(R.mipmap.haizw, 0, 0))
        lists.add(list7)

        val list8 = ArrayList<ImageBean>()
        list8.add(ImageBean(R.mipmap.haizw, 0, 0))
        list8.add(ImageBean(R.mipmap.haizw, 0, 0))
        list8.add(ImageBean(R.mipmap.haizw, 0, 0))
        list8.add(ImageBean(R.mipmap.haizw, 0, 0))
        list8.add(ImageBean(R.mipmap.haizw, 0, 0))
        list8.add(ImageBean(R.mipmap.haizw, 0, 0))
        list8.add(ImageBean(R.mipmap.haizw, 0, 0))
        list8.add(ImageBean(R.mipmap.haizw, 0, 0))
        lists.add(list8)

        val list9 = ArrayList<ImageBean>()
        list9.add(ImageBean(R.mipmap.haizw, 0, 0))
        list9.add(ImageBean(R.mipmap.haizw, 0, 0))
        list9.add(ImageBean(R.mipmap.haizw, 0, 0))
        list9.add(ImageBean(R.mipmap.haizw, 0, 0))
        list9.add(ImageBean(R.mipmap.haizw, 0, 0))
        list9.add(ImageBean(R.mipmap.haizw, 0, 0))
        list9.add(ImageBean(R.mipmap.haizw, 0, 0))
        list9.add(ImageBean(R.mipmap.haizw, 0, 0))
        list9.add(ImageBean(R.mipmap.haizw, 0, 0))
        lists.add(list9)

        val nineGridAdapter = NineGridAdapter(this, lists, R.layout.item_nine_grid)
        nine_grid_recycler.layoutManager = LinearLayoutManager(this)
        nine_grid_recycler.adapter = nineGridAdapter
    }

    private fun initPicture() {
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

    private fun initFoldedText() {
        text.text = "111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送"
        text.setOnClickListener { Toast.makeText(this, "textView点击事件", Toast.LENGTH_SHORT).show() }
        text1.text = "111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送"
        parent1.setOnClickListener { Toast.makeText(this, "父View点击事件", Toast.LENGTH_SHORT).show() }
        text2.text = "111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送"
        text2.setOnClickListener { Toast.makeText(this, "textView点击事件", Toast.LENGTH_SHORT).show() }
        text3.text = "111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送"
        parent3.setOnClickListener { Toast.makeText(this, "父View点击事件", Toast.LENGTH_SHORT).show() }
    }

    private fun initView(position: Int) {
        if (position == 1) {
            round_image_view.visibility = View.GONE
            btn_toggle.visibility = View.VISIBLE
            btn_toggle.setOnToggleListener(OnToggleListener { isOpen ->
                Toast.makeText(
                    this,
                    "" + isOpen,
                    Toast.LENGTH_SHORT
                ).show()
            })
        } else {
            round_image_view.visibility = View.VISIBLE
            btn_toggle.visibility = View.GONE
        }
    }

}