package com.demo.customview.scan_qr_code

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.customview.R
import kotlinx.android.synthetic.main.activity_scan.*

/**
 * Author by YML, Date on 2021/3/12.
 * PS: Not easy to write code, please indicate.
 */
class ScanActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        btn_scan.setOnClickListener {

        }

    }

}