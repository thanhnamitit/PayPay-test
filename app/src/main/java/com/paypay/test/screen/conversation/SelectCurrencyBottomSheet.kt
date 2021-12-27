package com.paypay.test.screen.conversation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.paypay.test.R
import com.paypay.test.domain.model.Currency
import java.lang.ref.WeakReference

class SelectCurrencyBottomSheet : BottomSheetDialogFragment(), OnSelected<Currency> {

    lateinit var controller: SelectCurrencyController

    private val currencies: List<Currency>
        get() = arguments?.getParcelableArray(EXTRA_CURRENCIES)?.toList() as List<Currency>

    private val searchEdt
        get() = view?.findViewById<EditText>(R.id.edt_search)
    private val recyclerView
        get() = view?.findViewById<EpoxyRecyclerView>(R.id.recycler_view)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_select_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = SelectCurrencyController(currencies, WeakReference(this))
        recyclerView?.setControllerAndBuildModels(controller)
        searchEdt?.doOnTextChanged { text, _, _, _ ->
            controller.searchingText = text.toString().trim().lowercase()
        }
    }

    companion object {

        private const val EXTRA_CURRENCIES = "extra_currencies"

        fun newInstance(currencies: List<Currency>): SelectCurrencyBottomSheet {
            val args = Bundle()
            args.putParcelableArray(EXTRA_CURRENCIES, currencies.toTypedArray())
            val fragment = SelectCurrencyBottomSheet()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onSelected(value: Currency) {
        (parentFragment as? OnSelected<Currency>)?.onSelected(value)
        dismiss()
    }
}