package com.easyinc.currencyexchange.common.extentions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun <T> androidLazy(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snacks = Snackbar.make(this,message,length)
    snacks.show()
}

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}

fun Long.isNotValid(): Boolean{
    val currentTime = System.currentTimeMillis()
    val diff = currentTime - this
    val seconds = diff / 1000
    val minutes = seconds / 60

    return minutes.toInt() > 90
}

fun Int.toNextWeek(): Int{
    return this + (604800000 / 1000)
}