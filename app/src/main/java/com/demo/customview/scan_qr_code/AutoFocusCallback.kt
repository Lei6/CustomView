package com.demo.customview.scan_qr_code

import android.hardware.Camera
import android.os.Handler
import android.util.Log

/**
 * Author by YML, Date on 2021/3/12.
 * PS: Not easy to write code, please indicate.
 */
class AutoFocusCallback : Camera.AutoFocusCallback {
    private var autoFocusHandler: Handler? = null
    private var autoFocusMessage = 0
    fun setHandler(autoFocusHandler: Handler?, autoFocusMessage: Int) {
        this.autoFocusHandler = autoFocusHandler
        this.autoFocusMessage = autoFocusMessage
    }

    override fun onAutoFocus(success: Boolean, camera: Camera?) {
        if (autoFocusHandler != null) {
            val message = autoFocusHandler!!.obtainMessage(autoFocusMessage, success)
            autoFocusHandler!!.sendMessageDelayed(message, AUTOFOCUS_INTERVAL_MS)
            autoFocusHandler = null
        } else {
            Log.d(TAG, "Got auto-focus callback, but no handler for it")
        }
    }

    companion object {
        private val TAG = AutoFocusCallback::class.java.simpleName
        private const val AUTOFOCUS_INTERVAL_MS = 1500L
    }
}