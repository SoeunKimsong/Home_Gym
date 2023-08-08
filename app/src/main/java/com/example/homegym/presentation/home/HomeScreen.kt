package com.example.homegym.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.homegym.data.exercises.*
import com.example.homegym.presentation.navigation.Screen
import com.example.homegym.presentation.screen_items.ExerciseDetailDialog
import com.example.homegym.ui.theme.HomeGymTheme

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val nestedScrollConnection = rememberNestedScrollInteropConnection()

    val onclickWorkout: (Workout) -> Unit = { workout ->
        navController.navigate(Screen.Workout.route + "?workout_id=${workout.id}")
    }


    var dialogState by remember {
        mutableStateOf(false)
    }
    var exerciseDetailToShow: Exercise by remember {
        mutableStateOf(viewModel.getExerciseById(0))
    }
    val onExerciseDetailClick: (Exercise) -> Unit = {
        exerciseDetailToShow = it
        dialogState = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
            .verticalScroll(scrollState)
    ) {

        val workoutHistory by viewModel.getWorkoutHistory().collectAsState(initial = emptyMap())
        WorkoutStrike(
            workoutStrike = viewModel.getWorkoutStrike(workoutHistory),
            lastWeekHistory = viewModel.getLastWeekHistory(workoutHistory)
        )
        Spacer(modifier = Modifier.height(8.dp))

        val feature by remember {
            mutableStateOf(viewModel.getAllExercises().random())
        }
        FeaturedExercise(feature, onExerciseDetailClick)

        if (workoutHistory.isNotEmpty()) {
            val recentWorkout =
                viewModel.getWorkoutById(workoutHistory.values.first().last().workoutId)
            RecentWorkout(recentWorkout, onclickWorkout)
        } else {
            RecentWorkout(viewModel.getWorkoutById(0), onclickWorkout)
        }
        Spacer(modifier = Modifier.height(16.dp))


        Challenges(
            workouts = viewModel.getWorkoutByGroup(WorkoutGroup.Challenges),
            onclickWorkout = onclickWorkout
        )
        Spacer(modifier = Modifier.height(16.dp))

        val bodyPartWorkouts = viewModel.getWorkoutByGroup(WorkoutGroup.BodyPartWorkouts)
        AllBodyPartsWorkout(bodyPartWorkouts, onclickWorkout)
        Spacer(modifier = Modifier.height(16.dp))

        val warmup = viewModel.getExercisesByBestForBodyPart(BodyPartBestFor.Stretch)
        WarmupExercises(warmup, onExerciseDetailClick)

        DiscoverMore(
            changeScreen = {
                navController.navigate(Screen.Discover.route) {
                    popUpTo(Screen.Home.route) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
    }

    if (dialogState) {
        ExerciseDetailDialog(
            exercise = exerciseDetailToShow,
            onDismiss = {
                dialogState = false
            }
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun PreviewHome() {
    HomeGymTheme {
        Scaffold {
            HomeScreen(navController = rememberNavController())
        }
    }
}