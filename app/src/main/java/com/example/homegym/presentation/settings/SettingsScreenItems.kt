package com.example.homegym.presentation.settings

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.homegym.R
import com.example.homegym.data.exercises.ExerciseLevel
import com.example.homegym.presentation.screen_items.TextDescription
import com.example.homegym.presentation.screen_items.Title
import com.example.homegym.ui.theme.Orange1
import com.example.homegym.ui.theme.Orange3
import java.util.Calendar

@Composable
fun StartCountDownSettings(
    countdown: String,
    onClickToChange: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClickToChange() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dumbbell),
            contentDescription = "icon",
            modifier = Modifier.size(20.dp)
        )
        TextDescription(text = "Start Countdown", Modifier.padding(start = 8.dp))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$countdown secs",
            color = Orange1,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
    Divider()
}


@Composable
fun LevelSettings(
    level: String,
    onChoosing: (String) -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dumbbell),
            contentDescription = "icon",
            modifier = Modifier.size(20.dp)
        )
        TextDescription(text = "Exercise Level", Modifier.padding(start = 8.dp))
        Spacer(modifier = Modifier.weight(1f))

        var isExpanded by remember {
            mutableStateOf(false)
        }

        Box {
            Button(
                onClick = { isExpanded = true }, modifier = Modifier.width(100.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                Text(text = level)
            }
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.width(100.dp),
            ) {
                ExerciseLevel.values().forEach { level ->
                    DropdownMenuItem(onClick = {
                        onChoosing(level.name)
                        isExpanded = false
                    }) {
                        Text(text = level.name)
                    }
                }
            }
        }


    }
    Divider()
}


@Composable
fun TrainingRestSettings(
    rest: String,
    onClickToChange: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClickToChange() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dumbbell),
            contentDescription = "icon",
            modifier = Modifier.size(20.dp)
        )
        TextDescription(text = "Rest Countdown", Modifier.padding(start = 8.dp))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "$rest secs", color = Orange1, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
    Divider()
}


@Composable
fun NotificationSettings(
    time: String,
    context: Context,
    isChecked: Boolean,
    onCheckNotificationChange: (Boolean)->Unit,
    onTimePick: (Calendar) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically){
            Icon(
                painter = painterResource(id = R.drawable.ic_dumbbell),
                contentDescription = "icon",
                modifier = Modifier.size(20.dp)
            )
            TextDescription(text = "Remind Me to Workout", Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.weight(1f))
            Checkbox(checked = isChecked, onCheckedChange = onCheckNotificationChange)
        }
        Divider()
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .alpha(if (!isChecked) .5f else 1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_dumbbell),
                contentDescription = "icon",
                modifier = Modifier.size(20.dp)
            )
            TextDescription(text = "Remind Time", Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    val timeSet = Calendar.getInstance()
                    TimePickerDialog(
                        context,
                        { _, hour, min ->
                            timeSet[Calendar.HOUR_OF_DAY] = hour
                            timeSet[Calendar.MINUTE] = min
                            onTimePick(timeSet)
                        },
                        timeSet[Calendar.HOUR_OF_DAY],
                        timeSet[Calendar.MINUTE],
                        false
                    ).show()
                },
                modifier = Modifier.width(100.dp),
                contentPadding = PaddingValues(4.dp),
                enabled = isChecked
            ) {
                Text(text = time)
            }
        }
    }

    Divider()
}


@Composable
fun RestartSettings(
    onClick: ()->Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dumbbell),
            contentDescription = "icon",
            modifier = Modifier.size(20.dp)
        )
        TextDescription(text = "Restart Progress", Modifier.padding(start = 8.dp))
    }
    Divider()
}

@Composable
fun ChangeDurationValueDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var value by remember {
        mutableStateOf(10)
    }
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.surface)
        ) {
            Title(text = "Choose Value", modifier = Modifier.padding(16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { value -= 10 },
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(5.dp, RoundedCornerShape(10.dp))
                        .background(if (value > 10) Orange1 else Orange3, RoundedCornerShape(10.dp)),
                    enabled = value > 0
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_goleft),
                        contentDescription = "go left",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }

                Text(
                    text = value.toString(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Orange1
                )

                IconButton(
                    onClick = { value += 10 },
                    modifier = Modifier
                        .size(40.dp)
                        .shadow(5.dp, RoundedCornerShape(10.dp))
                        .background(
                            Orange1,
                            RoundedCornerShape(10.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_goright),
                        contentDescription = "go right",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }


            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = onDismiss, modifier = Modifier.padding(24.dp)) {
                    Text(text = "Cancel", color = Color.Red)
                }
                TextButton(
                    onClick = {
                        onConfirm(value)
                    },
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(text = "OK", color = Color.Blue)
                }
            }
        }
    }
}

@Composable
fun ConfirmDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    question: String
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.surface)
        ) {
            Title(text = "Confirm Your Action", modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp))
            Spacer(modifier = Modifier.height(24.dp))

            TextDescription(text = question, modifier = Modifier.padding(16.dp))

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(text = "Cancel", color = Color.Red)
                }
                TextButton(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    },
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(text = "OK", color = Color.Blue)
                }
            }
        }
    }
}

@Composable
fun AboutUs(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dumbbell),
            contentDescription = "icon",
            modifier = Modifier.size(20.dp)
        )
        TextDescription(text = "About Us", Modifier.padding(start = 8.dp))
    }
    Divider()
}