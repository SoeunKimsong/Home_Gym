package com.example.homegym.domain.repository

import com.example.homegym.domain.model.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun insertHistory(history: History)
    suspend fun deleteHistory(history: History)
    suspend fun clearHistory()
    fun getHistoryData(): Flow<List<History>>
}