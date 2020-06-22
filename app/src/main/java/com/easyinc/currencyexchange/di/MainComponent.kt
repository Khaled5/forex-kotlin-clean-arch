package com.easyinc.currencyexchange.di

import android.app.Application
import com.easyinc.currencyexchange.ForexApplication
import com.easyinc.currencyexchange.di.module.AppModule
import com.easyinc.currencyexchange.di.subcomponent.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    AppModule::class])
interface MainComponent: AndroidInjector<ForexApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MainComponent

    }

}