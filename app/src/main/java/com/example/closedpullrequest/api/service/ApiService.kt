package com.example.closedpullrequest.api.service

import com.example.closedpullrequest.BuildConfig
import com.example.closedpullrequest.api.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    fun create(
        apiInterceptor: ApiInterceptor,
        baseUrl: String
    ): ApiInterface {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(apiInterceptor)
        if (BuildConfig.DEBUG) {
            val interceptor =
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            okHttpClient.addInterceptor(interceptor)
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiInterface::class.java)
    }
}
