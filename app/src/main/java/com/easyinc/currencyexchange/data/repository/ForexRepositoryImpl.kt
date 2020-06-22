package com.easyinc.currencyexchange.data.repository

import com.easyinc.currencyexchange.common.extentions.toNextWeek
import com.easyinc.currencyexchange.data.mapper.CurrencyEntityMapper
import com.easyinc.currencyexchange.data.mapper.PartnerEntityMapper
import com.easyinc.currencyexchange.domain.IForexRepository
import com.easyinc.currencyexchange.domain.model.Currency
import com.easyinc.currencyexchange.domain.model.Partner
import com.easyinc.currencyexchange.remote.model.PairsQuery
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ForexRepositoryImpl @Inject constructor(
    private val forexService: ForexService,
    private val partnerEntityMapper: PartnerEntityMapper,
    private val currencyEntityMapper: CurrencyEntityMapper
): IForexRepository {

    private var dateFrom = 0
    private var dateTo = 0

    override fun loginPartner(partner: Partner): Single<String> {
        return forexService.loginPartner(partnerEntityMapper.mapTo(partner))
    }

    override fun getPairs(pairsQuery: PairsQuery): Observable<List<Currency>> {

        dateFrom = pairsQuery.to
        dateTo = pairsQuery.to.toNextWeek()

            return forexService.getPairs(pairsQuery).map { list ->
                list.map {
                    currencyEntityMapper.mapFrom(it)
                }
            }
    }

    override fun getMorePairs(pairsQuery: PairsQuery): Observable<List<Currency>> {
        if (dateFrom == 0 || dateTo == 0){
            dateFrom = pairsQuery.to
            dateTo = pairsQuery.to.toNextWeek()
        }

        dateFrom = dateTo
        dateTo = dateTo.toNextWeek()

        pairsQuery.from = dateTo
        pairsQuery.to = dateTo.toNextWeek()

        return forexService.getPairs(pairsQuery).map { list ->
            list.map {
                currencyEntityMapper.mapFrom(it)
            }
        }
    }
}