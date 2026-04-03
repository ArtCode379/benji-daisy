package com.benjianddaisy.store.di

import com.benjianddaisy.store.data.repository.CartRepository
import com.benjianddaisy.store.data.repository.BJDYSOnboardingRepo
import com.benjianddaisy.store.data.repository.OrderRepository
import com.benjianddaisy.store.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        BJDYSOnboardingRepo(
            bjdysOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}