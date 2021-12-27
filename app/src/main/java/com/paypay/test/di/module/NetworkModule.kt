package com.paypay.test.di.module

import com.paypay.test.data.remote.ApiInterceptor
import com.paypay.test.data.remote.service.CurrencyService
import com.paypay.test.di.qualifier.AccessKey
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    private external fun accessKey(): String

    @Provides
    @AccessKey
    fun provideAccessKey(): String {
        return accessKey()
    }

    @Provides
    fun provideOkHttp(apiInterceptor: ApiInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(apiInterceptor)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.currencylayer.com")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCurrencyService(retrofit: Retrofit): CurrencyService {
        return retrofit.create(CurrencyService::class.java)
    }

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}