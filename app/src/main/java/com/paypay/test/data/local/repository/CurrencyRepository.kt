package com.paypay.test.data.local.repository

import com.paypay.test.data.local.dao.CurrencyDao
import com.paypay.test.data.local.entity.CurrencyEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CurrencyLocalRepositoryImpl @Inject constructor(private val currencyDao: CurrencyDao) :
    CurrencyLocalRepository {
    override fun getCurrencies(): Observable<List<CurrencyEntity>> {
        return Observable.create {
            val currencies = currencyDao.getAllCurrencies()
            it.onNext(currencies)
            it.onComplete()
        }
    }

    override fun saveAllCurrencies(input: List<CurrencyEntity>): Completable {
        return Completable.create {
            currencyDao.insertAll(*input.toTypedArray())
            it.onComplete()
        }
    }
}