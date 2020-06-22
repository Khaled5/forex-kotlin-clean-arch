package com.easyinc.currencyexchange.base.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.easyinc.currencyexchange.MainActivity
import com.easyinc.currencyexchange.common.network_state.NetworkEvents
import com.easyinc.currencyexchange.presentation.ForexViewModel
import com.easyinc.currencyexchange.ui.mapper.CurrencyMapper
import com.easyinc.currencyexchange.ui.mapper.PartnerMapper
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment: DaggerFragment() {

    var isConnected: Boolean = false

    lateinit var compositeDisposable: CompositeDisposable

    lateinit var mainViewModel: ForexViewModel

    @Inject lateinit var prefs: SharedPreferences

    @Inject lateinit var currencyMapper: CurrencyMapper
    @Inject lateinit var partnerMapper: PartnerMapper

    lateinit var navController: NavController

    lateinit var mainActivity: MainActivity
    
    abstract fun layoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        compositeDisposable = CompositeDisposable()

        NetworkEvents.observe(viewLifecycleOwner, Observer {
            isConnected = it.state.isConnected
        })

        return inflater.inflate(layoutId(), container, false)
    }


    fun showLoading(show: Boolean){
        (activity as MainActivity).showLoading(show)
    }


}