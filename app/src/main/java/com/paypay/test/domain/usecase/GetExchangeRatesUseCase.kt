package com.paypay.test.domain.usecase

import com.paypay.test.data.remote.service.CurrencyService
import io.reactivex.Observable
import javax.inject.Inject

class GetExchangeRatesUseCase @Inject constructor(
    private val currencyService: CurrencyService
) {
    operator fun invoke(source: String): Observable<Map<String, Double>> {
        return currencyService.getExchangeRate(source).map { response ->
            if (!response.success) throw  Exception()
            response.quotes?.entries?.associate {
                Pair(it.key.replaceFirst(source, ""), it.value.toDouble())
            } ?: mapOf()
        }
    }
}