package com.example.homegym.presentation.progress

import androidx.lifecycle.ViewModel
import com.example.homegym.domain.model.History
import com.example.homegym.domain.repository.HistoryRepository
import com.example.homegym.domain.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val historyRepo: HistoryRepository,
    private val workoutRepo: WorkoutRepository
) : ViewModel() {

    fun getWorkoutById(workoutId: Int) = workoutRepo.getWorkoutById(workoutId)

    fun getExerciseById(exerciseId: Int) = workoutRepo.getExerciseById(exerciseId)


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

    fun getDayStrike(map: Map<LocalDate, List<History>>): Int{
        var strike = 0
        if(map.isNotEmpty()){
            var dateStrike = map.keys.first().plusDays(1)
            for((key, _) in map){
                if(key.plusDays(1) != dateStrike) break
                strike++
                dateStrike = key
            }
        }

        return strike
    }

    fun getWorkoutStrike(dayStrike: Int, map: Map<LocalDate, List<History>>): Int{
        var strike = 0
        if(map.isNotEmpty()){
            var date = map.keys.first()
            repeat(dayStrike){
                strike += map[date]?.size ?: 0
                date = date.minusDays(1)
            }
        }

        return strike
    }


}