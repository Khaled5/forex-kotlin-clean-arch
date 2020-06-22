package com.easyinc.currencyexchange.di.subcomponent

import com.easyinc.currencyexchange.MainActivity
import com.easyinc.currencyexchange.di.module.DataModule
import com.easyinc.currencyexchange.di.module.RepositoryModule
import com.easyinc.currencyexchange.di.module.ThreadingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule  {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class,ThreadingModule::class,DataModule::class,RepositoryModule::class])
    abstract fun mainActivity(): MainActivity

}