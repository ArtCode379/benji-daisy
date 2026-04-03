package com.benjianddaisy.store.di

import androidx.room.Room
import com.benjianddaisy.store.data.database.BJDYSDatabase
import org.koin.dsl.module

private const val DB_NAME = "bjdys_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = BJDYSDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<BJDYSDatabase>().cartItemDao() }

    single { get<BJDYSDatabase>().orderDao() }
}