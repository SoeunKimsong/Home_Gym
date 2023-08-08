package com.example.homegym.domain.model

import java.time.LocalDateTime

data class History(
    val id: Int? = null,
    val workoutId: Int,
    val dateTime: LocalDateTime
)
