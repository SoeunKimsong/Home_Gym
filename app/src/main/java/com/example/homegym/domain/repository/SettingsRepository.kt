package com.example.homegym.domain.repository

import kotlinx.coroutines.flow.Flow
import java.util.Calendar

interface SettingsRepository {
    suspend fun setStartCountDownDuration(duration: Int)
    fun getStartCountDownDuration(): Flow<Int>

    suspend fun setNotificationRemindTime(time: Calendar?)
    fun getNotificationRemindTime(): Flow<Long>

    suspend fun setExerciseLevel(level: String)
    fun getExerciseLevel(): Flow<String>

    suspend fun setRestDuration(duration: Int)
    fun getRestDuration(): Flow<Int>
}