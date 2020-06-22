package com.easyinc.currencyexchange.di.module

import com.easyinc.currencyexchange.data.repository.ForexRepositoryImpl
import com.easyinc.currencyexchange.data.repository.ForexService
import com.easyinc.currencyexchange.domain.IForexRepository
import com.easyinc.currencyexchange.remote.ForexServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideForexService(forexServiceImpl: ForexServiceImpl): ForexService

    @Binds
    abstract fun provideForexRepository(forexRepositoryImpl: ForexRepositoryImpl): IForexRepository

}