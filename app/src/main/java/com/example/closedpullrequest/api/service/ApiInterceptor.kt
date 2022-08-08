package com.example.closedpullrequest.api.service

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiInterceptor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val chainRequest = chain.request().newBuilder()
        val request: Request = chainRequest.build()
        return chain.proceed(request)
    }
}

