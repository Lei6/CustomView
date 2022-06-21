package com.demo.customview.sort

import android.app.Activity
import android.os.Bundle
import com.demo.customview.ContactUtil
import com.demo.customview.R
import kotlinx.android.synthetic.main.activity_info.*

class ContactInfoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val contactUtil = ContactUtil(this)
        tv_contact_info.text = contactUtil.contactInfo
    }
}