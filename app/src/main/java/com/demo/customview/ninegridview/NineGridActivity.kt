package com.demo.customview.ninegridview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.customview.R
import kotlinx.android.synthetic.main.activity_nine_grid.*

/**
 * Author by YML, Date on 2021/3/9.
 * PS: Not easy to write code, please indicate.
 */
class NineGridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nine_grid)

        val list1 = ArrayList<ImageBean>()
        val lists = ArrayList<List<ImageBean>>()
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

}