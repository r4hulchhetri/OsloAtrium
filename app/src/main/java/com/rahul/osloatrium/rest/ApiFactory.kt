package com.rahul.osloatrium.rest

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rahul.osloatrium.BuildConfig
import com.rahul.osloatrium.Constants
import com.rahul.osloatrium.rest.interceptors.RequestInterceptor
import com.rahul.osloatrium.rest.service.LoginService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    private val okHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(RequestInterceptor())
        .addInterceptor(getLoggingInterceptor())
        .connectTimeout(Constants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(Constants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(Constants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val loginService: LoginService = retrofit().create(LoginService::class.java)
}