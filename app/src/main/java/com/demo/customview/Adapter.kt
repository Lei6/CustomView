package com.demo.customview

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.demo.customview.adapter.CommonAdapter
import com.demo.customview.adapter.ViewHolder
import com.demo.customview.foldedtext.FoldedTextActivity
import com.demo.customview.music.MusicActivity
import com.demo.customview.ninegridview.NineGridActivity
import com.demo.customview.picture_processing.PictureActivity
import com.demo.customview.slidingdrawer.PoiActivity
import com.demo.customview.sort.SideActivity
import com.demo.customview.toggle.ToggleButtonActivity

/**
 * Author by YML, Date on 2021/2/26.
 * PS: Not easy to write code, please indicate.
 */
class Adapter(mContext: Context?, mData: ArrayList<BtnBean>?, mLayoutId: Int,activity:Activity) : CommonAdapter<BtnBean>(mContext, mData, mLayoutId) {
    private var mActivity:Activity? = null
    init {
        mActivity =  activity
    }

    override fun bindData(holder: ViewHolder?, t: BtnBean?, position: Int) {
        holder?.setText(R.id.tv_btn_title,t?.btnTitle)

        holder?.setViewOnClickListener(R.id.tv_btn_title) {
            when(position){
                0-> Jump(MusicActivity::class.java)
                1-> Jump(ToggleButtonActivity::class.java)
                2-> {
                   if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                       ActivityCompat.requestPermissions(mActivity!!, arrayOf(Manifest.permission.READ_CONTACTS), 201)
                   }else{
                       Jump(SideActivity::class.java)
                   }
                }
                3-> Jump(PoiActivity::class.java)
                4-> Jump(FoldedTextActivity::class.java)
                5-> Jump(NineGridActivity::class.java)
                6-> Jump(PictureActivity::class.java)
                7->{}

            }
        }

    }

    private fun Jump(clazz: Class<*>){
        var intent = Intent(mContext,clazz)
        mContext.startActivity(intent)
    }
}