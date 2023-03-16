package com.example.bagstore_14.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bagstore_14.model.data.Product


@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}