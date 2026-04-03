package com.benjianddaisy.store.di

import com.benjianddaisy.store.data.datastore.BJDYSOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { BJDYSOnboardingPrefs(androidContext()) }
}