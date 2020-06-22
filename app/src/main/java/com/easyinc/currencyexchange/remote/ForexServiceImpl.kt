package com.easyinc.currencyexchange.remote

import com.easyinc.currencyexchange.data.model.CurrencyEntity
import com.easyinc.currencyexchange.data.model.PartnerEntity
import com.easyinc.currencyexchange.data.repository.ForexService
import com.easyinc.currencyexchange.remote.mapper.CurrencyModelMapper
import com.easyinc.currencyexchange.remote.mapper.PartnerModelMapper
import com.easyinc.currencyexchange.remote.model.PairsQuery
import com.easyinc.currencyexchange.remote.service.ForexApi
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ForexServiceImpl @Inject constructor(
    private val forexApi: ForexApi,
    private val partnerModelMapper: PartnerModelMapper,
    private val currencyModelMapper: CurrencyModelMapper
): ForexService {

    override fun loginPartner(partner: PartnerEntity): Single<String> {
        return forexApi.loginPartner(partnerModelMapper.mapFrom(partner))
    }

    override fun getPairs(pairsQuery: PairsQuery): Observable<List<CurrencyEntity>> {
        return forexApi.getPairs(
            pairsQuery.token,
            pairsQuery.login,
            pairsQuery.tradingSystem,
            pairsQuery.pairs,
            pairsQuery.from,
            pairsQuery.to
        ).map {list ->
            list.map {
                currencyModelMapper.mapFrom(it)
            }
        }
    }
}