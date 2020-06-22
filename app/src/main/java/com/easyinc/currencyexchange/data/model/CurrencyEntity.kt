package com.easyinc.currencyexchange.data.model

data class CurrencyEntity(
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