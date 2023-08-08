package com.example.homegym.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(historyEntity: HistoryEntity)

    @Delete
    suspend fun deleteHistory(historyEntity: HistoryEntity)

    @Query("DELETE FROM tbl_history WHERE 1")
    suspend fun clearHistory()

    @Query("SELECT * FROM tbl_history WHERE 1")
    fun getHistory(): Flow<List<HistoryEntity>>
}