package com.example.closedpullrequest.di

import com.example.closedpullrequest.presentation.viewModel.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel(get()) }
}