package com.easyinc.currencyexchange.di.module

import com.easyinc.currencyexchange.base.usecase.JobExecutor
import com.easyinc.currencyexchange.base.usecase.PostExecutionThread
import com.easyinc.currencyexchange.base.usecase.ThreadExecutor
import com.easyinc.currencyexchange.base.usecase.UiThread
import dagger.Binds
import dagger.Module

@Module
abstract class ThreadingModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

}