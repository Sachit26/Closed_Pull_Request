package com.example.closedpullrequest

import android.app.Application
import com.example.closedpullrequest.di.apiKoinModule
import com.example.closedpullrequest.di.appKoinModule
import com.example.closedpullrequest.di.repositoryKoinModule
import com.example.closedpullrequest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        koinConfiguration()
    }

    private fun koinConfiguration() {
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                appKoinModule + apiKoinModule + repositoryKoinModule + viewModelModule
            )
        }
    }
}
