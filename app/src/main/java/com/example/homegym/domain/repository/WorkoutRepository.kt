package com.example.homegym.domain.repository

import com.example.homegym.data.exercises.*

interface WorkoutRepository {
    fun getExerciseById(exerciseId: Int): Exercise
    fun getAllExercise(): List<Exercise>
    fun getExerciseByBestForBodyPart(bodyPartBestFor: BodyPartBestFor): List<Exercise>

    fun getWorkoutById(workoutId: Int): Workout
    fun getWorkoutByName(name: WorkoutName): Workout
    fun getWorkoutByGroup(groupName: WorkoutGroup): List<Workout>
}