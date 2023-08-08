package com.example.homegym.di

import com.example.homegym.data.repository.HistoryRepositoryImpl
import com.example.homegym.data.repository.SettingsRepositoryImpl
import com.example.homegym.data.repository.WorkoutRepositoryImpl
import com.example.homegym.domain.repository.HistoryRepository
import com.example.homegym.domain.repository.SettingsRepository
import com.example.homegym.domain.repository.WorkoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWorkoutRepository(workoutRepositoryImpl: WorkoutRepositoryImpl): WorkoutRepository

    @Binds
    @Singleton
    abstract fun bindHistoryRepository(historyRepositoryImpl: HistoryRepositoryImpl): HistoryRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository

}