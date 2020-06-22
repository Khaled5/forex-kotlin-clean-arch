package com.easyinc.currencyexchange.domain

import com.easyinc.currencyexchange.domain.model.Currency
import com.easyinc.currencyexchange.domain.model.Partner
import com.easyinc.currencyexchange.remote.model.PairsQuery
import io.reactivex.Observable
import io.reactivex.Single

interface IForexRepository {

    fun loginPartner(partner: Partner): Single<String>

    fun getPairs(pairsQuery: PairsQuery): Observable<List<Currency>>

    fun getMorePairs(pairsQuery: PairsQuery): Observable<List<Currency>>

}