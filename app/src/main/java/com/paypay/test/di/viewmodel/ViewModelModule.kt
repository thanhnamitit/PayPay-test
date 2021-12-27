package com.paypay.test.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bind(factory: ViewModelFactory): ViewModelProvider.Factory
}