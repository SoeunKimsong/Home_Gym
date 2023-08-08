package com.example.homegym.presentation.workout

import androidx.lifecycle.ViewModel
import com.example.homegym.data.exercises.BodyPartBestFor
import com.example.homegym.data.exercises.WorkoutName
import com.example.homegym.domain.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val repository: WorkoutRepository
): ViewModel() {

    fun getWorkoutById(workoutId: Int) = repository.getWorkoutById(workoutId)
    fun getExerciseById(exerciseId: Int) = repository.getExerciseById(exerciseId)
}