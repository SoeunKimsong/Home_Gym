package com.example.homegym.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeTypeConverter::class)
abstract class HistoryDb: RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
}