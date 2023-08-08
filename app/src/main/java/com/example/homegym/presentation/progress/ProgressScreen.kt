package com.example.homegym.presentation.progress

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.homegym.ui.theme.HomeGymTheme
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun ProgressScreen(
    viewModel: ProgressViewModel = hiltViewModel()
) {
    val workoutHistory by viewModel.getWorkoutHistory().collectAsState(emptyMap())
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxWidth()
    ) {

        val dayStrike = viewModel.getDayStrike(workoutHistory)
        val workoutStrike = viewModel.getWorkoutStrike(dayStrike, workoutHistory)
        val timeSpent = "${(workoutStrike*20)/60}H" + if((workoutStrike*20)%60 > 0) "${(workoutStrike*20)%60}MN" else ""
        WorkoutStrike(
            dayStrike = dayStrike,
            workoutStrike = workoutStrike,
            timeSpent = timeSpent
        )

        var selectedDate by remember {
            mutableStateOf(LocalDate.now())
        }
        var monthYearDisplay by remember {
            mutableStateOf(LocalDate.now())
        }
        WorkoutCalendar(
            map = workoutHistory,
            monthYearDisplay = monthYearDisplay,
            selectedDate = selectedDate,
            onForwardMonth = { monthYearDisplay = monthYearDisplay.plusMonths(1) },
            onBackwardMonth = { monthYearDisplay = monthYearDisplay.minusMonths(1) },
            onSelectDate = { date ->
                selectedDate =
                    selectedDate.withMonth(monthYearDisplay.monthValue).withDayOfMonth(date)
            }
        )

        WorkoutHistory(
            history = workoutHistory[selectedDate] ?: emptyList(),
            date = selectedDate
        ) {
            viewModel.getWorkoutById(it)
        }
        Spacer(modifier = Modifier.height(16.dp))


        val lastWeekWorkout = viewModel.getLastWeekHistory(workoutHistory)
        CalorieBurnGraph(
            currentDay = LocalDateTime.now(),
            history = lastWeekWorkout,
            findWorkoutById = { viewModel.getWorkoutById(it) }
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun PreviewProgress() {
    HomeGymTheme {
        Scaffold {
            ProgressScreen()
        }
    }
}