package com.example.homegym.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.homegym.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.util.Calendar
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val app: Application
    ): SettingsRepository {

    private val preferenceName = "SETTINGS"
    private val Context.dataStore by preferencesDataStore(
        name = preferenceName
    )

    private val keyStartCountDown = intPreferencesKey("START")
    private val keyRestCountDown = intPreferencesKey("REST")
    private val keyExerciseLevel = stringPreferencesKey("LEVEL")
    private val keyNotificationTime = longPreferencesKey("NOTIFICATION")


    override suspend fun setStartCountDownDuration(duration: Int) {
        app.applicationContext.dataStore.edit {preference ->
            preference[keyStartCountDown] = duration
        }
    }
    override fun getStartCountDownDuration(): Flow<Int> {
        return app.applicationContext.dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map {preference ->
                preference[keyStartCountDown] ?: 10
            }
    }


    override suspend fun setNotificationRemindTime(time: Calendar?) {
        app.applicationContext.dataStore.edit { preference ->
            preference[keyNotificationTime] = time?.timeInMillis ?: 0
        }
    }
    override fun getNotificationRemindTime(): Flow<Long> {
        return app.applicationContext.dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map {preference ->
                preference[keyNotificationTime] ?: 0
            }
    }


    override suspend fun setExerciseLevel(level: String) {
        app.applicationContext.dataStore.edit {preference ->
            preference[keyExerciseLevel] = level
        }
    }
    override fun getExerciseLevel(): Flow<String> {
        return app.applicationContext.dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map {preference ->
                preference[keyExerciseLevel] ?: "Medium"
            }
    }

    override suspend fun setRestDuration(duration: Int) {
        app.applicationContext.dataStore.edit {preference ->
            preference[keyRestCountDown] = duration
        }
    }
    override fun getRestDuration(): Flow<Int> {
        return app.applicationContext.dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map {preference ->
                preference[keyRestCountDown] ?: 60
            }
    }


}