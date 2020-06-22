package com.easyinc.currencyexchange.ui.mapper

interface ViewMapper<in P, out V> {

    fun mapTo(type: P) : V
}