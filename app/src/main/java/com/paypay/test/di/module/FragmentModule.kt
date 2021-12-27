package com.paypay.test.di.module

import androidx.lifecycle.ViewModel
import com.paypay.test.di.viewmodel.ViewModelKey
import com.paypay.test.screen.conversation.CurrencyConversationViewModel
import com.paypay.test.screen.conversation.CurrencyConversionFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector(
        modules = [
            Binding::class,
        ]
    )
    abstract fun contribute(): CurrencyConversionFragment
}

@Module
abstract class Binding {
    @Binds
    @IntoMap
    @ViewModelKey(CurrencyConversationViewModel::class)
    abstract fun bind(viewModel: CurrencyConversationViewModel): ViewModel
}
