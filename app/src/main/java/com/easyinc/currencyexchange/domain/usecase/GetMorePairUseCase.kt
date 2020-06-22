package com.easyinc.currencyexchange.domain.usecase

import com.easyinc.currencyexchange.base.usecase.ObservableUseCase
import com.easyinc.currencyexchange.base.usecase.PostExecutionThread
import com.easyinc.currencyexchange.base.usecase.ThreadExecutor
import com.easyinc.currencyexchange.domain.IForexRepository
import com.easyinc.currencyexchange.domain.model.Currency
import com.easyinc.currencyexchange.remote.model.PairsQuery
import io.reactivex.Observable
import javax.inject.Inject

class GetMorePairUseCase @Inject constructor(
    private val forexRepository: IForexRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): ObservableUseCase<List<Currency>,PairsQuery>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: PairsQuery?): Observable<List<Currency>> {
        return forexRepository.getMorePairs(params!!)
    }
}