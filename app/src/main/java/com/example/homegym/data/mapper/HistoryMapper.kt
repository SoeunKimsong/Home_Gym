package com.example.homegym.data.mapper

import com.example.homegym.data.local.HistoryEntity
import com.example.homegym.domain.model.History

fun HistoryEntity.toHistory(): History{
    return History(
        id,
        workoutId,
        dateTime
    )
}

fun History.toHistoryEntity(): HistoryEntity{
    return HistoryEntity(
        id ?: 0,
        workoutId,
        dateTime
    )
}