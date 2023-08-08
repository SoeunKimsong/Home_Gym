package com.example.homegym.data.repository

import com.example.homegym.data.exercises.AllWorkouts
import com.example.homegym.data.exercises.AllWorkouts.absWorkout
import com.example.homegym.data.exercises.AllWorkouts.armsWorkout
import com.example.homegym.data.exercises.AllWorkouts.athletesChoicesWorkout
import com.example.homegym.data.exercises.AllWorkouts.bellyFatBurnerWorkout
import com.example.homegym.data.exercises.AllWorkouts.chestWorkout
import com.example.homegym.data.exercises.AllWorkouts.crunchesChallengeWorkout
import com.example.homegym.data.exercises.AllWorkouts.getRidManBoobWorkout
import com.example.homegym.data.exercises.AllWorkouts.immunityBoosterWorkout
import com.example.homegym.data.exercises.AllWorkouts.plankChallengeWorkout
import com.example.homegym.data.exercises.AllWorkouts.pushUpChallengeWorkout
import com.example.homegym.data.exercises.AllWorkouts.rippedVAbsWorkout
import com.example.homegym.data.exercises.AllWorkouts.sevenMnAbsWorkout
import com.example.homegym.data.exercises.AllWorkouts.sevenMnClassicWorkout
import com.example.homegym.data.exercises.AllWorkouts.sevenMnFatBurningWorkout
import com.example.homegym.data.exercises.AllWorkouts.stayInShapeWorkout
import com.example.homegym.data.exercises.BodyPartBestFor
import com.example.homegym.data.exercises.Exercise
import com.example.homegym.data.exercises.Workout
import com.example.homegym.data.exercises.WorkoutExercises.Companion.allExercises
import com.example.homegym.data.exercises.WorkoutGroup
import com.example.homegym.data.exercises.WorkoutName
import com.example.homegym.domain.repository.WorkoutRepository
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor():  WorkoutRepository{
    override fun getExerciseById(exerciseId: Int): Exercise {
        return allExercises[exerciseId]
    }

    override fun getAllExercise(): List<Exercise> {
        return allExercises
    }

    override fun getExerciseByBestForBodyPart(bodyPartBestFor: BodyPartBestFor): List<Exercise> {
        return allExercises.filter { exercise ->
            exercise.bodyPartBestFor == bodyPartBestFor
        }
    }

    override fun getWorkoutById(workoutId: Int): Workout {
        return AllWorkouts.allWorkouts.find {
            it.id == workoutId
        } ?: absWorkout
    }

    override fun getWorkoutByName(name: WorkoutName): Workout {
        return when(name){
            WorkoutName.AbsWorkout -> absWorkout
            WorkoutName.ChestWorkout -> chestWorkout
            WorkoutName.ArmWorkout -> armsWorkout
            WorkoutName.BellyFatBurnerWorkout -> bellyFatBurnerWorkout
            WorkoutName.RippedVAbsWorkout -> rippedVAbsWorkout
            WorkoutName.GetRidManBoobWorkout -> getRidManBoobWorkout
            WorkoutName.ImmunityBoosterWorkout -> immunityBoosterWorkout
            WorkoutName.AthletesChoicesWorkout -> athletesChoicesWorkout
            WorkoutName.StayInShapeWorkout -> stayInShapeWorkout
            WorkoutName.SevenMnClassicWorkout -> sevenMnClassicWorkout
            WorkoutName.SevenMnFatBurningWorkout -> sevenMnFatBurningWorkout
            WorkoutName.SevenMnAbsWorkout -> sevenMnAbsWorkout
            WorkoutName.PlankChallengeWorkout -> plankChallengeWorkout
            WorkoutName.PushUpChallengeWorkout -> pushUpChallengeWorkout
            WorkoutName.CrunchesChallengeWorkout -> crunchesChallengeWorkout
        }
    }

    override fun getWorkoutByGroup(groupName: WorkoutGroup): List<Workout> {
        return when(groupName){
            WorkoutGroup.BodyPartWorkouts -> listOf(
                absWorkout, chestWorkout, armsWorkout
            )
            WorkoutGroup.BurnerWorkouts -> listOf(
                bellyFatBurnerWorkout, rippedVAbsWorkout, getRidManBoobWorkout
            )
            WorkoutGroup.QuarantineWorkouts -> listOf(
                immunityBoosterWorkout, athletesChoicesWorkout, stayInShapeWorkout
            )
            WorkoutGroup.FastWorkouts -> listOf(
                sevenMnClassicWorkout, sevenMnFatBurningWorkout, sevenMnAbsWorkout
            )
            WorkoutGroup.Challenges -> listOf(
                plankChallengeWorkout, pushUpChallengeWorkout, crunchesChallengeWorkout
            )
        }
    }

}