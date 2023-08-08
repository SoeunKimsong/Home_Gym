package com.example.homegym.presentation.discover

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
import com.example.homegym.data.exercises.BodyPartBestFor
import com.example.homegym.data.exercises.Exercise
import com.example.homegym.data.exercises.Workout
import com.example.homegym.data.exercises.WorkoutGroup
import com.example.homegym.presentation.navigation.Screen
import com.example.homegym.presentation.screen_items.ExerciseDetailDialog
import com.example.homegym.ui.theme.HomeGymTheme

@Composable
fun DiscoverScreen(
    navController: NavController,
    viewModel: DiscoverViewModel = hiltViewModel()
){
    val scrollState = rememberScrollState()
    val nestedScrollConnection = rememberNestedScrollInteropConnection()

    val onclickWorkout: (Workout)->Unit = {
        navController.navigate(Screen.Workout.route + "?workout_id=${it.id}")
    }

    var dialogState by remember{
        mutableStateOf(false)
    }
    var exerciseDetailToShow by remember {
        mutableStateOf(viewModel.getExerciseById(0))
    }
    val onExerciseDetailClick: (Exercise)->Unit = {
        exerciseDetailToShow = it
        dialogState = true
    }


    Column(modifier = Modifier
        .nestedScroll(nestedScrollConnection)
        .verticalScroll(scrollState)
        .fillMaxWidth()
    ){

        val burnerWorkouts = viewModel.getWorkoutByGroup(WorkoutGroup.BurnerWorkouts)
        BurnerWorkout(burnerWorkouts, onclickWorkout)
        Spacer(modifier = Modifier.height(16.dp))

        val fastWorkouts = viewModel.getWorkoutByGroup(WorkoutGroup.FastWorkouts)
        FastWorkout(fastWorkouts, onclickWorkout)
        Spacer(modifier = Modifier.height(16.dp))

        val challenges = viewModel.getWorkoutByGroup(WorkoutGroup.Challenges)
        Challenges(challenges, onclickWorkout)
        Spacer(modifier = Modifier.height(16.dp))

        val quarantineWorkouts = viewModel.getWorkoutByGroup(WorkoutGroup.QuarantineWorkouts)
        QuarantineWorkout(quarantineWorkouts, onclickWorkout)
        Spacer(modifier = Modifier.height(16.dp))

        val stretches = viewModel.getExercisesByBestForBodyPart(BodyPartBestFor.Stretch)
        AfterWorkoutStretch(stretches, onExerciseDetailClick)
        Spacer(modifier = Modifier.height(32.dp))
    }

    if(dialogState){
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
fun PreviewDiscover() {
    HomeGymTheme {
        Scaffold {
            DiscoverScreen(navController = rememberNavController())
        }
    }
}