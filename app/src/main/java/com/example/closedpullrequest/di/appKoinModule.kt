package com.example.closedpullrequest.di

import com.example.closedpullrequest.BuildConfig
import com.example.closedpullrequest.api.ApiConstants
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appKoinModule  = module{
    single(named(ApiConstants.API_BASE_URL)) { BuildConfig.API_BASE_URL }
}