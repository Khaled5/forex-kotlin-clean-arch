package com.easyinc.currencyexchange.remote.model

import com.easyinc.currencyexchange.common.extentions.toNextWeek


data class PairsQuery(
    val token: String,
    val login: String,
    val tradingSystem: String = "3", //Static for testing
    val pairs: String = "EURUSD,GBPUSD,USDJPY,USDCHF,USDCAD,AUDUSD,NZDUSD", //Static for testing
    var from: Int = 1489964823, //Static for testing
    var to: Int = from.toNextWeek()
)