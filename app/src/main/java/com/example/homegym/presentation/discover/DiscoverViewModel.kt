package com.example.homegym.presentation.discover

import androidx.lifecycle.ViewModel
import com.example.homegym.data.exercises.BodyPartBestFor
import com.example.homegym.data.exercises.WorkoutGroup
import com.example.homegym.data.exercises.WorkoutName
import com.example.homegym.domain.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: WorkoutRepository
): ViewModel() {
    fun getWorkoutById(workoutId: Int) = repository.getWorkoutById(workoutId)
    fun getWorkoutByGroup(groupName: WorkoutGroup) = repository.getWorkoutByGroup(groupName)

    fun getExercisesByBestForBodyPart(bodyPartBestFor: BodyPartBestFor) = repository.getExerciseByBestForBodyPart(bodyPartBestFor = bodyPartBestFor)
    fun getExerciseById(exerciseId: Int) = repository.getExerciseById(exerciseId)
}