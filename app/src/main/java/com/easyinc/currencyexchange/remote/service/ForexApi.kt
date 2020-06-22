package com.easyinc.currencyexchange.remote.service

import com.easyinc.currencyexchange.remote.model.CurrencyModel
import com.easyinc.currencyexchange.remote.model.PartnerModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface ForexApi {

    @POST("api/Authentication/RequestMoblieCabinetApiToken")
    fun loginPartner(@Body partner : PartnerModel): Single<String>


    @GET("clientmobile/GetAnalyticSignals/{login}")
    fun getPairs(
        @Header("passkey") token: String,
        @Path("login") login : String,
        @Query("tradingsystem") tradingsystem: String,
        @Query("pairs") pairs: String,
        @Query("from") from: Int,
        @Query("to") to: Int
    ): Observable<List<CurrencyModel>>

}