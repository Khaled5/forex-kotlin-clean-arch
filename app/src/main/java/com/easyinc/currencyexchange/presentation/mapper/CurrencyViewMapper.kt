package com.easyinc.currencyexchange.presentation.mapper

import com.easyinc.currencyexchange.domain.model.Currency
import com.easyinc.currencyexchange.presentation.model.CurrencyView
import javax.inject.Inject

class CurrencyViewMapper @Inject constructor(): Mapper<CurrencyView, Currency> {
    override fun mapTo(type: Currency): CurrencyView {
        return CurrencyView(
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