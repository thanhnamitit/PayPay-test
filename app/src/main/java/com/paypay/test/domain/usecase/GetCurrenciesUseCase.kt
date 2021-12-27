package com.paypay.test.domain.usecase

import com.paypay.test.data.local.entity.CurrencyEntity
import com.paypay.test.data.local.repository.CurrencyLocalRepository
import com.paypay.test.data.remote.service.CurrencyService
import com.paypay.test.domain.model.Currency
import io.reactivex.Observable
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val currencyService: CurrencyService,
    private val currencyLocalRepository: CurrencyLocalRepository
) {
    operator fun invoke(): Observable<List<Currency>> {
        return currencyLocalRepository.getCurrencies().flatMap {
            if (it.isNotEmpty()) Observable.just(it.map { entity ->
                Currency(
                    code = entity.code,
                    name = entity.name
                )
            }) else currencyService.getCurrencies().map { response ->
                response.currencies.entries.map { entry ->
                    Currency(entry.key, entry.value)
                }
            }.flatMap { currencies ->
                currencyLocalRepository.saveAllCurrencies(currencies.map { currency ->
                    CurrencyEntity(
                        code = currency.code,
                        name = currency.name
                    )
                }).toSingle { currencies }.toObservable()
            }
        }
    }
}