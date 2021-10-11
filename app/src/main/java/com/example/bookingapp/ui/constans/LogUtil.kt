package com.example.bookingapp.ui.constans

import android.util.Log

object LogUtil {

    var isDebug = false
    var TAG = "Log"

    fun init(isDebug: Boolean) {
        this.isDebug = isDebug
    }

    fun v(className: String, functionName: String, msg: String) {
        if (isDebug) {
            Log.v(TAG, "$className.$functionName - $msg")
        }
    }

    fun v(msg: String) {
        if (isDebug) {
            Log.v(TAG, msg)
        }
    }

    fun debug(tag: String, msg: String) {
        if (isDebug) {
            Log.d(tag, msg)
        }
    }

    fun debug(msg: String) {
        if (isDebug) {
            Log.d(TAG, msg)
        }
    }

    fun info(tag: String, msg: String) {
        if (isDebug) {
            Log.i(tag, msg)
        }
    }

    fun info(msg: String) {
        if (isDebug) {
            Log.i(TAG, msg)
        }
    }

    fun warning(tag: String, msg: String) {
        if (isDebug) {
            Log.w(tag, msg)
        }
    }

    fun warning(msg: String) {
        if (isDebug) {
            Log.w(TAG, msg)
        }
    }

    fun error(tag: String, msg: String) {
        if (isDebug) {
            Log.e(tag, msg)
        }
    }

    fun error(msg: String) {
        if (isDebug) {
            Log.e(TAG, msg)
        }
    }
}
