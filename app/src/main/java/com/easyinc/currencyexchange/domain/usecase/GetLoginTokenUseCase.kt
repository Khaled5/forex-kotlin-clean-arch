package com.easyinc.currencyexchange.domain.usecase

import com.easyinc.currencyexchange.base.usecase.PostExecutionThread
import com.easyinc.currencyexchange.base.usecase.SingleUseCase
import com.easyinc.currencyexchange.base.usecase.ThreadExecutor
import com.easyinc.currencyexchange.domain.IForexRepository
import com.easyinc.currencyexchange.domain.model.Partner
import io.reactivex.Single
import javax.inject.Inject

class GetLoginTokenUseCase @Inject constructor(
    private val forexRepository: IForexRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): SingleUseCase<String,Partner>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: Partner?): Single<String> {
        return forexRepository.loginPartner(params!!)
    }

}