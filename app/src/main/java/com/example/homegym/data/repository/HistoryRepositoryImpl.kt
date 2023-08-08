package com.example.homegym.data.repository

import com.example.homegym.data.local.HistoryDao
import com.example.homegym.data.mapper.toHistory
import com.example.homegym.data.mapper.toHistoryEntity
import com.example.homegym.domain.model.History
import com.example.homegym.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(private val dao: HistoryDao): HistoryRepository {
    override suspend fun insertHistory(history: History) {
        return dao.insertHistory(history.toHistoryEntity())
    }

    override suspend fun deleteHistory(history: History) {
        return dao.deleteHistory(history.toHistoryEntity())
    }

    override suspend fun clearHistory() {
        dao.clearHistory()
    }

    override fun getHistoryData(): Flow<List<History>> {
        return dao.getHistory().transform { list ->
            emit(list.map { it.toHistory() })
        }

    }
}