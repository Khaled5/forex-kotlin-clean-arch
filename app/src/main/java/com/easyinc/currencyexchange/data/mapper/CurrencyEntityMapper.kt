package com.easyinc.currencyexchange.data.mapper

import com.easyinc.currencyexchange.data.model.CurrencyEntity
import com.easyinc.currencyexchange.domain.model.Currency
import com.easyinc.currencyexchange.remote.model.CurrencyModel
import javax.inject.Inject

class CurrencyEntityMapper @Inject constructor(): EntityMapper<CurrencyEntity,Currency> {

    override fun mapFrom(entity: CurrencyEntity): Currency {
        return Currency(
            entity.actualTime,
            entity.cmd,
            entity.comment,
            entity.id,
            entity.pair,
            entity.period,
            entity.price,
            entity.sl,
            entity.tp,
            entity.tradingSystem
        )
    }

    override fun mapTo(entity: Currency): CurrencyEntity {
        return CurrencyEntity(
            entity.actualTime,
            entity.cmd,
            entity.comment,
            entity.id,
            entity.pair,
            entity.period,
            entity.price,
            entity.sl,
            entity.tp,
            entity.tradingSystem
        )
    }
}