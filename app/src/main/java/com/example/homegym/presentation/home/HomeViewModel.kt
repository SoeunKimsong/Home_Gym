package com.example.homegym.presentation.home

import androidx.lifecycle.ViewModel
import com.example.homegym.data.exercises.BodyPartBestFor
import com.example.homegym.data.exercises.WorkoutGroup
import com.example.homegym.data.exercises.WorkoutName
import com.example.homegym.domain.model.History
import com.example.homegym.domain.repository.HistoryRepository
import com.example.homegym.domain.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val workoutRepo: WorkoutRepository,
    private val historyRepo: HistoryRepository
):ViewModel() {
    fun getWorkoutById(workoutId: Int) = workoutRepo.getWorkoutById(workoutId)
    fun getWorkoutByGroup(groupName: WorkoutGroup) = workoutRepo.getWorkoutByGroup(groupName)

    fun getExercisesByBestForBodyPart(bodyPartBestFor: BodyPartBestFor) = workoutRepo.getExerciseByBestForBodyPart(bodyPartBestFor = bodyPartBestFor)
    fun getExerciseById(exerciseId: Int) = workoutRepo.getExerciseById(exerciseId)
    fun getAllExercises() = workoutRepo.getAllExercise()

    fun getWorkoutHistory(): Flow<Map<LocalDate, List<History>>> {
        return historyRepo.getHistoryData().transform { list ->
            emit(
                list.groupBy { history ->
                    history.dateTime.toLocalDate()
                }.toSortedMap { a, b ->
                    b.compareTo(a)
                }
            )
        }
    }

    fun getLastWeekHistory(result: Map<LocalDate, List<History>>): List<List<History>> {
        var day = LocalDate.now()
        val list = mutableListOf<List<History>>()
        repeat(7) {
            list.add(result[day] ?: emptyList())
            day = day.minusDays(1)
        }
        return list
    }

    fun getWorkoutStrike(result: Map<LocalDate, List<History>>): Int{
        var strike = 0
        if(result.isNotEmpty()){
            var dateStrike = result.keys.first().plusDays(1)
            for((key, list) in result){
                if(key.plusDays(1) != dateStrike) break
                strike += list.size
                dateStrike = key
            }
        }
        return strike
    }
}