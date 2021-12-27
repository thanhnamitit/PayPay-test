package com.paypay.test.di.module

import com.paypay.test.screen.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(
        modules = [FragmentModule::class]
    )
    abstract fun contribute(): MainActivity
}
