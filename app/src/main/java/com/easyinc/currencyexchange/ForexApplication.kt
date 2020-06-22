package com.easyinc.currencyexchange

import com.easyinc.currencyexchange.common.network_state.NetworkStateHolder.registerConnectivityBroadcaster
import com.easyinc.currencyexchange.di.DaggerMainComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class ForexApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMainComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        registerConnectivityBroadcaster()
    }
}