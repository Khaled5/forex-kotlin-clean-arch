package com.easyinc.currencyexchange.presentation.model

data class CurrencyView(
    val actualTime: Int,
    val cmd: Int,
    val comment: String,
    val id: Int,
    val pair: String,
    val period: String,
    val price: Double,
    val sl: Double,
    val tp: Double,
    val tradingSystem: Int
)