package com.easyinc.currencyexchange.ui.mapper

import com.easyinc.currencyexchange.presentation.model.CurrencyView
import com.easyinc.currencyexchange.ui.model.Currency
import javax.inject.Inject

class CurrencyMapper @Inject constructor(): ViewMapper<CurrencyView,Currency>{
    override fun mapTo(type: CurrencyView): Currency {
        return Currency(
            type.actualTime,
            type.cmd,
            type.comment,
            type.id,
            type.pair,
            type.period,
            type.price,
            type.sl,
            type.tp,
            type.tradingSystem
        )
    }
}