package com.example.homegym.presentation.exercise

import com.example.homegym.data.exercises.Workout

data class ExerciseScreenState(
    val workout: Workout? = null,
    val exerciseIndex: Int? = null,
    val action: ExerciseAction = ExerciseAction.Start,
    val isPaused: Boolean = false,
)

enum class ExerciseAction{
    Start, Do, Rest, Finish
}
