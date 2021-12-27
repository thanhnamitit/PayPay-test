package com.paypay.test.screen.conversation

import com.airbnb.epoxy.EpoxyController
import com.paypay.test.domain.model.Async
import com.paypay.test.itemRate

class CurrencyConversationController : EpoxyController() {
    var state: CurrencyConversationState? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        state?.let { state ->
            val rates = state.rates.invoke() ?: mapOf()
            val currency = state.currencies?.invoke() ?: return
            currency.map {
                val rate = rates[it.code] ?: 0.0
                val amount = when (state.rates) {
                    is Async.Fail -> "Error"
                    is Async.Loading -> "Loading..."
                    is Async.Success -> (rate * state.enteredAmount).toString()
                }
                itemRate {
                    id(it.code)
                    code(it.code)
                    amount(amount)
                }
            }
        }
    }
}