package com.example.homegym.data.exercises

import androidx.annotation.DrawableRes

data class Exercise(
    val id: Int,
    val name: String,
    val instruction: String,
    @DrawableRes val image: Int,
    val type: ExerciseType,
    val level: ExerciseLevel,
    val bodyPartBestFor: BodyPartBestFor,
    val tip: String? = null,
)
