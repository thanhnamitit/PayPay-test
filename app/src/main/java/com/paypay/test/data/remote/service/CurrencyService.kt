package com.paypay.test.data.remote.service

import com.paypay.test.data.remote.response.CurrenciesResponse
import com.paypay.test.data.remote.response.ExchangeRatesResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("/list")
    fun getCurrencies(): Observable<CurrenciesResponse>

    @GET("/live")
    fun getExchangeRate(
        @Query("source") source: String? = null
    ): Observable<ExchangeRatesResponse>
}
