package com.paypay.test.screen.conversation

import com.paypay.test.domain.model.Async
import com.paypay.test.domain.model.Currency

data class CurrencyConversationState(
    val currencies: Async<List<Currency>>? = Async.Loading(),
    val selected: Currency? = null,
    val rates: Async<Map<String, Double>> = Async.Loading(),
    val enteredAmount : Double = 0.0
)