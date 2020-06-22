package com.easyinc.mappractice.common

import android.util.Log

object Logger {

    private const val TAG = "Forex"

    fun dt(value: String) {
        Log.d(TAG, "Thread Name: ${Thread.currentThread().name} - $value")
    }
}