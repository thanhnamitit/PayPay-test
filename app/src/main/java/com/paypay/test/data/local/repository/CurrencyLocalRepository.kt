package com.paypay.test.data.local.repository

import com.paypay.test.data.local.entity.CurrencyEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CurrencyLocalRepository {
    fun getCurrencies(): Observable<List<CurrencyEntity>>
    fun saveAllCurrencies(input: List<CurrencyEntity>): Completable
}