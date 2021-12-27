package com.paypay.test.screen.conversation

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.airbnb.epoxy.EpoxyRecyclerView
import com.paypay.test.R
import com.paypay.test.di.viewmodel.ViewModelFactory
import com.paypay.test.domain.model.Currency
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CurrencyConversionFragment : DaggerFragment(R.layout.fragment_currency_conversation),
    OnSelected<Currency> {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel by viewModels<CurrencyConversationViewModel> {
        viewModelFactory
    }

    private val selectedTextView: TextView?
        get() = view?.findViewById(R.id.tv_selected)

    private val recyclerView: EpoxyRecyclerView?
        get() = view?.findViewById(R.id.recycler_view)
    private val selectCurrencyBtn: View?
        get() = view?.findViewById(R.id.layout_selected)
    private val edtAmount: EditText?
        get() = view?.findViewById(R.id.edt_amount)

    private val controller = CurrencyConversationController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView?.setControllerAndBuildModels(controller)

        selectCurrencyBtn?.setOnClickListener {
            val state = viewModel.stateValue
            state.currencies?.invoke()?.let {
                SelectCurrencyBottomSheet.newInstance(it).show(childFragmentManager, "")
            }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            controller.state = it
            selectedTextView?.text = it.selected?.code
        }

        edtAmount?.doOnTextChanged { text, _, _, _ ->
            viewModel.updateAmount(text.toString().toDoubleOrNull() ?: 0.0)
        }
    }

    override fun onSelected(value: Currency) {
        viewModel.updateSelectedCurrency(value)
    }
}