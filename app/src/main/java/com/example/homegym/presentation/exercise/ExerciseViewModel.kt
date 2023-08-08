package com.example.homegym.presentation.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homegym.data.exercises.Exercise
import com.example.homegym.data.exercises.ExerciseLevel
import com.example.homegym.data.exercises.ExerciseType
import com.example.homegym.data.exercises.Workout
import com.example.homegym.domain.model.History
import com.example.homegym.domain.repository.HistoryRepository
import com.example.homegym.domain.repository.SettingsRepository
import com.example.homegym.domain.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val workoutRepo: WorkoutRepository,
    private val historyRepo: HistoryRepository,
    settingsRepo: SettingsRepository
) : ViewModel() {
    fun getWorkoutById(workoutId: Int) = workoutRepo.getWorkoutById(workoutId)

    fun getExerciseById(exerciseId: Int) = workoutRepo.getExerciseById(exerciseId)

    val startCountdown = settingsRepo.getStartCountDownDuration()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = 10
        )
    val restCountdown = settingsRepo.getRestDuration()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 60
        )

    fun getExerciseAmount(exercise: Exercise): Int? {
        return when (exercise.type) {
            ExerciseType.CountAmount -> {
                when (exercise.level) {
                    ExerciseLevel.Easy -> 16
                    ExerciseLevel.Medium -> 12
                    ExerciseLevel.Hard -> 10
                }
            }

            ExerciseType.CountAmountTwoSide -> {
                when (exercise.level) {
                    ExerciseLevel.Easy -> 14
                    ExerciseLevel.Medium -> 10
                    ExerciseLevel.Hard -> 8
                }
            }

            else -> null
        }
    }

    fun getExerciseDuration(exercise: Exercise): Int? {
        return when (exercise.type) {
            ExerciseType.CountDuration -> {
                when (exercise.level) {
                    ExerciseLevel.Easy -> 60
                    ExerciseLevel.Medium -> 45
                    ExerciseLevel.Hard -> 30
                }
            }

            ExerciseType.CountDurationTwoSide -> {
                when (exercise.level) {
                    ExerciseLevel.Easy -> 40
                    ExerciseLevel.Medium -> 30
                    ExerciseLevel.Hard -> 20
                }
            }

            else -> null
        }
    }

    val state = MutableStateFlow(
        ExerciseScreenState()
    )
    private var exerciseIndex = 0
    private var currentWorkout: Workout? = null

    fun startNewWorkout(workout: Workout) {
        currentWorkout = workout
        state.value = state.value.copy(
            workout = currentWorkout,
            exerciseIndex = exerciseIndex
        )
    }

    fun startExercise() {
        state.value = state.value.copy(
            action = ExerciseAction.Do
        )
    }

    fun skipToNextExercise() {
        currentWorkout?.let {
            exerciseIndex++
            if (exerciseIndex < it.exercises.size) {
                state.value = state.value.copy(
                    exerciseIndex = exerciseIndex,
                    action = ExerciseAction.Rest
                )
            } else {
                state.value = state.value.copy(
                    action = ExerciseAction.Finish
                )
            }
        }
    }

    fun gotoPreviousExercise() {
        currentWorkout?.let {
            if(exerciseIndex>0){
                exerciseIndex--
                state.value = state.value.copy(
                    exerciseIndex = exerciseIndex,
                    action = ExerciseAction.Rest
                )
            }
        }
    }

    fun saveHistory(history: History) {
        viewModelScope.launch {
            historyRepo.insertHistory(history)
        }
    }


}