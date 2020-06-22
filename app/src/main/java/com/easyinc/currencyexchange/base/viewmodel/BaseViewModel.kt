package com.easyinc.currencyexchange.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.easyinc.currencyexchange.common.functional.Resource
import com.easyinc.currencyexchange.presentation.model.CurrencyView
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val loginTokenLiveData by lazy { MutableLiveData<Resource<String>>() }
    val pairsLiveData by lazy { MutableLiveData<Resource<List<CurrencyView>>>() }
    val morePairsLiveData by lazy { MutableLiveData<Resource<List<CurrencyView>>>() }
    val message by lazy { MutableLiveData<Resource<String>>() }

    val observeLoginToken: LiveData<Resource<String>>
        get() = loginTokenLiveData

    val observePairs: LiveData<Resource<List<CurrencyView>>>
        get() = pairsLiveData

    val observeMorePairs: LiveData<Resource<List<CurrencyView>>>
        get() = morePairsLiveData

    val observeMessage: LiveData<Resource<String>>
    get() = message

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }
}