package com.example.closedpullrequest.di

import com.example.closedpullrequest.api.ApiConstants
import com.example.closedpullrequest.api.service.ApiInterceptor
import com.example.closedpullrequest.api.service.ApiService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val apiKoinModule = module {
    single {
        ApiService.create(
            get(), get(named(ApiConstants.API_BASE_URL))
        )
    }
    single { ApiInterceptor() }
}