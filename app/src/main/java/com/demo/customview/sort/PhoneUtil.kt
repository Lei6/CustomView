package com.demo.customview.sort

import android.content.Context
import android.provider.ContactsContract

/**
 *created by 姚明亮
 *Time：2019/10/17 09:41
 */
class PhoneUtil constructor(val context: Context) {

    companion object {
        //手机号
        const val NUM = ContactsContract.CommonDataKinds.Phone.NUMBER
        //姓名
        const val NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME

        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

    }

    fun getPhone(): List<SortBean> {
        val phones = ArrayList<SortBean>()
        val cr = context.contentResolver
        val cursor = cr.query(phoneUri, arrayOf(NUM, NAME), null, null, null)
        while (cursor!!.moveToNext()) {
            val phone = SortBean(cursor.getString(cursor.getColumnIndex(NAME)), cursor.getString(cursor.getColumnIndex(NUM)))
            phones.add(phone)
        }
        return phones
    }
}