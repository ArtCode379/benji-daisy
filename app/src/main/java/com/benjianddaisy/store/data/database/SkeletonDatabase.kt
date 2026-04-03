package com.benjianddaisy.store.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.benjianddaisy.store.data.dao.CartItemDao
import com.benjianddaisy.store.data.dao.OrderDao
import com.benjianddaisy.store.data.database.converter.Converters
import com.benjianddaisy.store.data.entity.CartItemEntity
import com.benjianddaisy.store.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BJDYSDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}