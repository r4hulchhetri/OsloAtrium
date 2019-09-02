package com.rahul.osloatrium.rest.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
            .newBuilder()
            .addHeader("Platform", "android")
            .addHeader("Accept", "application/json")
            .addHeader("Content-type", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}