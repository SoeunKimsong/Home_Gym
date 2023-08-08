package com.example.homegym.presentation.exercise

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.homegym.R
import com.example.homegym.data.exercises.Exercise
import com.example.homegym.data.exercises.ExerciseType
import com.example.homegym.domain.model.History
import com.example.homegym.presentation.navigation.Screen
import com.example.homegym.presentation.screen_items.ExerciseDetailDialog
import com.example.homegym.presentation.screen_items.SoundEffectPlayer
import com.example.homegym.presentation.screen_items.StopExerciseDialog
import com.example.homegym.ui.theme.HomeGymTheme
import java.time.LocalDateTime

@Composable
fun ExerciseScreen(
    workoutId: Int,
    navController: NavController,
    viewModel: ExerciseViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    val workout = remember {
        viewModel.getWorkoutById(workoutId)
    }

    val startCountdown by viewModel.startCountdown.collectAsState()
    val restDuration by viewModel.restCountdown.collectAsState()

    var shouldShowStopExerciseDialog by remember {
        mutableStateOf(false)
    }
    val onBackPressed = {
        shouldShowStopExerciseDialog = !shouldShowStopExerciseDialog
    }

    val bellSound = SoundEffectPlayer(context = context, soundEffect = R.raw.bell)
    val applauseSound = SoundEffectPlayer(context = context, soundEffect = R.raw.applause)

    viewModel.startNewWorkout(workout)


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.exerciseIndex == null) {
            CircularProgressIndicator()
        } else {
            // for exercise detail dialog
            var dialogState by remember {
                mutableStateOf(false)
            }
            val onExerciseDetailClick: (Exercise) -> Unit = {
                dialogState = true
            }

            state.exerciseIndex?.let {
                if (state.action != ExerciseAction.Finish) {
                    val exercise = workout.exercises[it]
                    val amountExercise = viewModel.getExerciseAmount(exercise)
                    val durationExercise = viewModel.getExerciseDuration(exercise)
                    val todo = amountExercise ?: durationExercise ?: 100

                    when (state.action) {
                        ExerciseAction.Start, ExerciseAction.Rest -> {
                            val isStarting = state.action == ExerciseAction.Start
                            StartRestScreen(
                                exerciseIndex = it,
                                workout = workout,
                                amount = todo,
                                durationWait = if (isStarting) startCountdown else restDuration,
                                isResting = !isStarting,
                                goToExercise = {
                                    bellSound.playSound()
                                    viewModel.startExercise()
                                },
                                onExerciseDetailClick = onExerciseDetailClick,
                                popBackStack = onBackPressed
                            )
                        }

                        ExerciseAction.Do -> {
                            when (exercise.type) {
                                ExerciseType.CountAmount, ExerciseType.CountAmountTwoSide -> {
                                    CountAmountExercise(
                                        exerciseIndex = it,
                                        workout = workout,
                                        amount = todo,
                                        onNextClick = {
                                            viewModel.skipToNextExercise()
                                            bellSound.playSound()
                                        },
                                        onPreviousClick = {
                                            viewModel.gotoPreviousExercise()
                                            bellSound.playSound()
                                        },
                                        onExerciseDetailClick = onExerciseDetailClick,
                                        popBackStack = onBackPressed
                                    )
                                }

                                ExerciseType.CountDuration, ExerciseType.CountDurationTwoSide -> {
                                    CountDurationExercise(
                                        exerciseIndex = it,
                                        workout = workout,
                                        duration = todo,
                                        onNextClick = {
                                            bellSound.playSound()
                                            viewModel.skipToNextExercise()
                                        },
                                        onPreviousClick = {
                                            bellSound.playSound()
                                            viewModel.gotoPreviousExercise()
                                        },
                                        onExerciseDetailClick = onExerciseDetailClick,
                                        popBackStack = onBackPressed
                                    )
                                }
                            }
                        }

                        else -> {}
                    }
                    if (dialogState) {
                        ExerciseDetailDialog(
                            exercise = exercise,
                            onDismiss = {
                                dialogState = false
                            }
                        )
                    }
                } else {
                    FinishScreen(
                        workout = workout,
                        onFinished = {
                            applauseSound.playSound()

                            // save history to database
                            viewModel.saveHistory(
                                History(
                                    workoutId = workoutId,
                                    dateTime = LocalDateTime.now()
                                )
                            )
                        },
                        onExit = {
                            // stop sound
                            bellSound.stopSound()
                            applauseSound.stopSound()

                            // exit to progress screen
                            navController.popBackStack(
                                inclusive = false,
                                route = Screen.Home.route
                            )
                            navController.navigate(Screen.Progress.route) {
                                popUpTo(Screen.Home.route) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    }

    // when press back button during exercise
    BackHandler(onBack = onBackPressed)

    // show exit exercise dialog
    AnimatedVisibility(visible = shouldShowStopExerciseDialog) {
        StopExerciseDialog(
            onOK = {navController.popBackStack()},
            onDismiss = {shouldShowStopExerciseDialog = false}
        )
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun PreviewHome() {
    HomeGymTheme {
        Scaffold {
            ExerciseScreen(1, navController = rememberNavController())
        }
    }
}