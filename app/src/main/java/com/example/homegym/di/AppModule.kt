package com.example.homegym.di

import android.app.Application
import androidx.room.Room
import com.example.homegym.data.local.HistoryDao
import com.example.homegym.data.local.HistoryDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHistoryDao(historyDb: HistoryDb): HistoryDao{
        return historyDb.getHistoryDao()
    }

    @Provides
    @Singleton
    fun provideHistoryDb(appContext: Application): HistoryDb{
        return Room.databaseBuilder(
            appContext,
            HistoryDb::class.java,
            "history_db"
        ).build()
    }
}