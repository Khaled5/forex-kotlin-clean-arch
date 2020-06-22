package com.easyinc.currencyexchange.presentation.mapper

interface Mapper<out V, in D> {

    fun mapTo(type: D): V
}