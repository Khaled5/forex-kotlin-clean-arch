package com.easyinc.currencyexchange.presentation

import com.easyinc.currencyexchange.base.viewmodel.BaseViewModel
import com.easyinc.currencyexchange.common.functional.Resource
import com.easyinc.currencyexchange.domain.usecase.GetLoginTokenUseCase
import com.easyinc.currencyexchange.domain.usecase.GetMorePairUseCase
import com.easyinc.currencyexchange.domain.usecase.GetPairsUseCase
import com.easyinc.currencyexchange.presentation.mapper.CurrencyViewMapper
import com.easyinc.currencyexchange.presentation.mapper.PartnerViewMapper
import com.easyinc.currencyexchange.presentation.model.PartnerView
import com.easyinc.currencyexchange.remote.model.PairsQuery
import javax.inject.Inject

class ForexViewModel @Inject constructor(
    private val getLoginTokenUseCase: GetLoginTokenUseCase,
    private val getPairsUseCase: GetPairsUseCase,
    private val getMorePairUseCase: GetMorePairUseCase,
    private val currencyMapper: CurrencyViewMapper,
    private val partnerMapper: PartnerViewMapper
): BaseViewModel(){


    fun loginPartner(partner: PartnerView){
        message.postValue(Resource.Loading(null))
        compositeDisposable.add(
            getLoginTokenUseCase.execute(
                partnerMapper.mapTo(partner)
            ).subscribe(
                {
                    loginTokenLiveData.postValue(Resource.Success(it))
                },{
                    message.postValue(Resource.Error(it.message!!))
                }
            )
        )
    }

    fun getPairs(token: String, login: String){
        message.postValue(Resource.Loading(null))
        compositeDisposable.add(
            getPairsUseCase.execute(
                PairsQuery(token,login)
            ).map {list ->
                list.map {
                    currencyMapper.mapTo(it)
                }
            }.subscribe(
                {
                    pairsLiveData.postValue(Resource.Success(it))
                },{
                    message.postValue(Resource.Error(it.message!!))
                }
            )
        )
    }

    fun getMorePairs(token: String, login: String){
        morePairsLiveData.postValue(Resource.Loading(null))
        compositeDisposable.add(
            getMorePairUseCase.execute(
                PairsQuery(token,login)
            ).map {list ->
                list.map {
                    currencyMapper.mapTo(it)
                }
            }.subscribe(
                {
                    morePairsLiveData.postValue(Resource.Success(it))
                },{
                    message.postValue(Resource.Error(it.message!!))
                }
            )
        )
    }

}