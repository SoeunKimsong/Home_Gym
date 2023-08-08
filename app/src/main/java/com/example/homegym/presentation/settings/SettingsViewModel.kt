package com.example.homegym.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homegym.domain.repository.HistoryRepository
import com.example.homegym.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepo: SettingsRepository,
    private val historyRepo: HistoryRepository
): ViewModel() {

    val startCountdown = settingsRepo.getStartCountDownDuration()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = 10
        )
    fun setStartCountdownDuration(duration: Int){
        viewModelScope.launch {
            settingsRepo.setStartCountDownDuration(duration)
        }
    }

    val restCountdown = settingsRepo.getRestDuration()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 60
        )
    fun setRestDuration(duration: Int){
        viewModelScope.launch {
            settingsRepo.setRestDuration(duration)
        }
    }

    val exerciseLevel = settingsRepo.getExerciseLevel()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = "Medium"
        )
    fun setExerciseLevel(level: String){
        viewModelScope.launch {
            settingsRepo.setExerciseLevel(level)
        }
    }

    val notificationTime = settingsRepo.getNotificationRemindTime()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0
        )
    fun setNotificationRemindTime(time: Calendar?){
        viewModelScope.launch {
            settingsRepo.setNotificationRemindTime(time)
        }
    }


    // reset progress
    fun clearHistory(){
        viewModelScope.launch {
            historyRepo.clearHistory()
        }
    }

}