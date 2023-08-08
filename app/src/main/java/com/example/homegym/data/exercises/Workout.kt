package com.example.homegym.data.exercises

import androidx.annotation.DrawableRes

data class Workout(
    val id: Int,
    val name: String,
    val exercises: List<Exercise>,
    val calorieBurn: Int,
    @DrawableRes val image: Int? = null,
    @DrawableRes val icon: Int? = null
)
