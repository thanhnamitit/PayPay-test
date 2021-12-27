package com.paypay.test.data.remote

import com.paypay.test.di.qualifier.AccessKey
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class ApiInterceptor @Inject constructor(@AccessKey val accessKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("access_key", accessKey)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}