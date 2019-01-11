package com.worldpay.techtest.api

import android.app.Application
import com.worldpay.techtest.R
import com.squareup.moshi.Moshi
import com.worldpay.techtest.api.payment.PaymentApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
internal object ApiModule {

    @JvmStatic
    @Provides
    fun okHttpClient(apiConfig: ApiConfig): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)

        apiConfig.interceptors.map {
            okHttpClient.addInterceptor(it)
        }

        return okHttpClient.build()
    }

    @JvmStatic
    @Provides
    fun moshi(): Moshi = Moshi.Builder().build()

    @JvmStatic
    @Provides
    fun moshiConverterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @JvmStatic
    @Provides
    fun retrofit(application: Application, okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(application.getString(R.string.app_config_api_url))
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()
    }

    @JvmStatic
    @Provides
    fun api(retrofit: Retrofit): PaymentApi = retrofit.create(PaymentApi::class.java)
}