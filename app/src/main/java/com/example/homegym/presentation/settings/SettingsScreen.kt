package com.example.homegym.presentation.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.homegym.NotificationReceiver
import com.example.homegym.presentation.navigation.Screen
import com.example.homegym.presentation.screen_items.TextName
import com.example.homegym.ui.theme.Blue
import com.example.homegym.ui.theme.HomeGymTheme
import com.example.homegym.ui.theme.Orange2
import com.example.homegym.util.cancelScheduleDailyNotification
import com.example.homegym.util.checkNotificationPermission
import com.example.homegym.util.formatHourMinute
import com.example.homegym.util.scheduleDailyNotification
import java.util.Calendar

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val startCountdown by viewModel.startCountdown.collectAsState()
    val restCountdown by viewModel.restCountdown.collectAsState()
    val exerciseLevel by viewModel.exerciseLevel.collectAsState()
    val notificationTime by viewModel.notificationTime.collectAsState()

    var showChangeStartCountdownDialog by remember {
        mutableStateOf(false)
    }
    var showChangeRestCountdownDialog by remember {
        mutableStateOf(false)
    }
    var showRestartSettingsDialog by remember {
        mutableStateOf(false)
    }

    var startCountDownInput by remember {
        mutableStateOf(0)
    }
    var restCountDownInput by remember {
        mutableStateOf(0)
    }

    var shouldSave by remember {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextName(text = "Settings", modifier = Modifier.padding(16.dp), color = Orange2)
            TextButton(
                onClick = {
                    viewModel.setStartCountdownDuration(startCountDownInput)
                    viewModel.setRestDuration(restCountDownInput)
                    shouldSave = false
                },
                enabled = shouldSave
            ) {
                TextName(
                    text = "Save",
                    modifier = Modifier.padding(16.dp),
                    color = if (shouldSave) Blue else Color.Gray
                )
            }
        }

        StartCountDownSettings(
            countdown = if(startCountDownInput!=0) startCountDownInput.toString() else startCountdown.toString(),
            onClickToChange = { showChangeStartCountdownDialog = true }
        )

        TrainingRestSettings(
            rest = if(restCountDownInput!=0) restCountDownInput.toString() else restCountdown.toString(),
            onClickToChange = { showChangeRestCountdownDialog = true }
        )

        LevelSettings(
            level = exerciseLevel,
            onChoosing = { viewModel.setExerciseLevel(it) }
        )

        var isNotificationAllow: Boolean? by remember {
            mutableStateOf(null)
        }

        val receiver = Intent(context, NotificationReceiver::class.java)
        NotificationSettings(
            time = if(notificationTime != 0L) formatHourMinute(Calendar.Builder().setInstant(notificationTime).build())
            else "None",
            isChecked = isNotificationAllow ?: (notificationTime != 0L),
            onTimePick = {
                viewModel.setNotificationRemindTime(it)

                scheduleDailyNotification(
                    context = context,
                    time = it,
                    receiver = receiver
                )
            },
            context = context,
            onCheckNotificationChange = {
                if (checkNotificationPermission(context)) {
                    isNotificationAllow = it
                    if(!it) {
                        cancelScheduleDailyNotification(context, receiver)
                        viewModel.setNotificationRemindTime(null)
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Please allow permission to send notification",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )


        RestartSettings(
            onClick = { showRestartSettingsDialog = true }
        )


        AboutUs(
            onClick = {
                navController.navigate(Screen.AboutUs.route)
            }
        )
    }

    if (showChangeStartCountdownDialog) {
        ChangeDurationValueDialog(
            onDismiss = {
                showChangeStartCountdownDialog = false
                startCountDownInput = 0
                        },
            onConfirm = {
                showChangeStartCountdownDialog = false
                startCountDownInput = it
                shouldSave = true
            }
        )
    }
    if (showChangeRestCountdownDialog) {
        ChangeDurationValueDialog(
            onDismiss = {
                showChangeRestCountdownDialog = false
                restCountDownInput = 0
                        },
            onConfirm = {
                showChangeRestCountdownDialog = false
                restCountDownInput = it
                shouldSave = true
            }
        )
    }
    if (showRestartSettingsDialog) {
        ConfirmDialog(
            onDismiss = { showRestartSettingsDialog = false },
            onConfirm = {
                showRestartSettingsDialog = false
                viewModel.clearHistory()
            },
            question = "Are you sure you want to restart your progress?"
        )
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun PreviewSettings() {
    HomeGymTheme {
        Scaffold {
            SettingsScreen(navController = rememberNavController())
        }
    }
}