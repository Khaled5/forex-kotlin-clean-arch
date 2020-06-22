package com.easyinc.currencyexchange.remote.mapper

import com.easyinc.currencyexchange.data.model.CurrencyEntity
import com.easyinc.currencyexchange.remote.model.CurrencyModel
import javax.inject.Inject

class CurrencyModelMapper @Inject constructor(): ModelMapper<CurrencyModel,CurrencyEntity> {
    override fun mapFrom(map: CurrencyModel): CurrencyEntity {
        return CurrencyEntity(
            map.actualTime,
            map.cmd,
            map.comment,
            map.id,
            map.pair,
            map.period,
            map.price,
            map.sl,
            map.tp,
            map.tradingSystem
        )
    }
}