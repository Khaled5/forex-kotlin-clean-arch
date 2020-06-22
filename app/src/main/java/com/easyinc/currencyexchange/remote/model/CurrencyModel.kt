package com.easyinc.currencyexchange.remote.model


import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("ActualTime")
    val actualTime: Int,
    @SerializedName("Cmd")
    val cmd: Int,
    @SerializedName("Comment")
    val comment: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Pair")
    val pair: String,
    @SerializedName("Period")
    val period: String,
    @SerializedName("Price")
    val price: Double,
    @SerializedName("Sl")
    val sl: Double,
    @SerializedName("Tp")
    val tp: Double,
    @SerializedName("TradingSystem")
    val tradingSystem: Int
)