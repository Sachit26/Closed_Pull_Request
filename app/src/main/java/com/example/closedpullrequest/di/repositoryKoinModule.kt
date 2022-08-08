package com.example.closedpullrequest.di

import com.example.closedpullrequest.repository.ApiRepository
import org.koin.dsl.module

val repositoryKoinModule = module{
    single { ApiRepository(get()) }
}