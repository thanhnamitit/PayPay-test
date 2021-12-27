package com.paypay.test.screen.conversation

import com.airbnb.epoxy.EpoxyController
import com.paypay.test.domain.model.Currency
import com.paypay.test.itemCurrency
import navanvi.edm.app.ext.observable
import java.lang.ref.WeakReference

class SelectCurrencyController(
    private val currencies: List<Currency>,
    private val listener: WeakReference<OnSelected<Currency>?>,
) : EpoxyController() {
    var searchingText: String by observable("") { requestModelBuild() }
    override fun buildModels() {
        currencies.filter {
            it.code.lowercase().contains(searchingText)
                    || it.name.lowercase().contains(searchingText)
                    || searchingText.isEmpty()
        }
            .forEach {
                itemCurrency {
                    id(it.code)
                    code(it.code)
                    name(it.name)
                    onClick { _ ->
                        this@SelectCurrencyController.listener.get()?.onSelected(it)
                    }
                }
            }
    }
}

