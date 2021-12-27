package com.paypay.test.screen.conversation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paypay.test.domain.model.Async
import com.paypay.test.domain.model.Currency
import com.paypay.test.domain.usecase.GetCurrenciesUseCase
import com.paypay.test.domain.usecase.GetExchangeRatesUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class CurrencyConversationViewModel @Inject constructor(
    val getCurrenciesUseCase: GetCurrenciesUseCase,
    val getExchangeRatesUseCase: GetExchangeRatesUseCase,
) : ViewModel() {

    private val _state = MutableLiveData(CurrencyConversationState())
    private val selectedCurrencySubject = BehaviorSubject.create<Currency>().apply {
        onNext(Currency("USD", "USD"))
    }
    val state: LiveData<CurrencyConversationState>
        get() = _state
    val stateValue
        get() = _state.value ?: CurrencyConversationState()

    init {
        getCurrencies()
        setupSelectedCurrency()
    }

    private fun setupSelectedCurrency() {
        selectedCurrencySubject.distinctUntilChanged().doOnNext {
            emit(
                stateValue.copy(
                    selected = it,
                    rates = Async.Loading()
                )
            )
        }.switchMap { currency ->
            getExchangeRatesUseCase(currency.code)
                .subscribeOn(Schedulers.io()).map<Async<Map<String, Double>>> {
                    Async.Success(it)
                }
                .onErrorReturn {
                    Async.Fail(it)
                }
        }.subscribe {
            emit(stateValue.copy(rates = it))
        }
    }

    private fun emit(state: CurrencyConversationState) {
        _state.postValue(state)
    }

    private fun getCurrencies() {
        getCurrenciesUseCase().execute {
            copy(currencies = it)
        }
    }

    fun updateSelectedCurrency(value: Currency) {
        selectedCurrencySubject.onNext(value);
    }

    fun updateAmount(input: Double) = emit(stateValue.copy(enteredAmount = input))

    private fun <T> Observable<T>.execute(
        block: CurrencyConversationState.(Async<T>) -> CurrencyConversationState
    ) {
        this.subscribeOn(Schedulers.io())
            .doOnSubscribe { emit(block(stateValue, Async.Loading())) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ emit(block(stateValue, Async.Success(it))) },
                { emit(block(stateValue, Async.Fail(it))) })
    }


}