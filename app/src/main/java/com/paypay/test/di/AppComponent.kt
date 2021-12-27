package com.paypay.test.di

import com.paypay.test.App
import com.paypay.test.di.module.ActivityModule
import com.paypay.test.di.module.AppModule
import com.paypay.test.di.module.DatabaseModule
import com.paypay.test.di.module.NetworkModule
import com.paypay.test.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        DatabaseModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<App> {
    override fun inject(instance: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): ApplicationComponent
    }
}