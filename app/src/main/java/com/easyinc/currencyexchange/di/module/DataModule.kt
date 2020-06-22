package com.easyinc.currencyexchange.di.module

import android.app.Application
import android.content.SharedPreferences
import com.easyinc.currencyexchange.common.utils.PreferenceHelper
import com.easyinc.currencyexchange.remote.service.ForexApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    fun provideForexApi(retrofit: Retrofit): ForexApi{
        return retrofit.create(ForexApi::class.java)
    }

    @Provides
    fun provideSharedPreference(application: Application): SharedPreferences{
        return PreferenceHelper.defaultPrefs(application.applicationContext)
    }

}