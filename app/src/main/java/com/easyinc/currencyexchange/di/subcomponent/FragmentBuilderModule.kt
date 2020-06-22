package com.easyinc.currencyexchange.di.subcomponent

import com.easyinc.currencyexchange.ui.LoginFragment
import com.easyinc.currencyexchange.ui.PairsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun providePairsFragment(): PairsFragment

}