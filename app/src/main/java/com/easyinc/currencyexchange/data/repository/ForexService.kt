package com.easyinc.currencyexchange.data.repository

import com.easyinc.currencyexchange.data.model.CurrencyEntity
import com.easyinc.currencyexchange.data.model.PartnerEntity
import com.easyinc.currencyexchange.remote.model.PairsQuery
import io.reactivex.Observable
import io.reactivex.Single

interface ForexService {

    fun loginPartner(partner: PartnerEntity): Single<String>

    fun getPairs(pairsQuery: PairsQuery): Observable<List<CurrencyEntity>>

}