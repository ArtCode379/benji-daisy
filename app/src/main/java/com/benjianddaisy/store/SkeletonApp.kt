package com.benjianddaisy.store

import android.app.Application
import com.benjianddaisy.store.di.dataModule
import com.benjianddaisy.store.di.dispatcherModule
import com.benjianddaisy.store.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class BJDYSApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@BJDYSApp)
            modules(appModules)
        }
    }
}