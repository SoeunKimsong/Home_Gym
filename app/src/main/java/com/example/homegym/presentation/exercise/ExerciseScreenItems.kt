package com.example.homegym.presentation.exercise

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homegym.R
import com.example.homegym.data.exercises.Exercise
import com.example.homegym.data.exercises.ExerciseLevel
import com.example.homegym.data.exercises.ExerciseType
import com.example.homegym.data.exercises.Workout
import com.example.homegym.presentation.screen_items.TextDescription
import com.example.homegym.presentation.screen_items.TextName
import com.example.homegym.presentation.screen_items.Title
import com.example.homegym.ui.theme.Orange1
import com.example.homegym.ui.theme.Orange2
import com.example.homegym.ui.theme.Orange3
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun StartRestScreen(
    exerciseIndex: Int,
    workout: Workout,
    isResting: Boolean,
    amount: Int,
    durationWait: Int,
    popBackStack: ()->Unit,
    goToExercise: () -> Unit,
    onExerciseDetailClick: (Exercise) -> Unit
) {
    val exercise = workout.exercises[exerciseIndex]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange1)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isResting) "REST" else "START IN",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))

                var isTimerRunning by remember {
                    mutableStateOf(true)
                }

                TimerCountdownDigital(
                    totalTime = durationWait,
                    isTimerRunning = isTimerRunning,
                    textColor = Color.White,
                    onFinished = goToExercise
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            isTimerRunning = !isTimerRunning
                        },
                        shape = RoundedCornerShape(30.dp),
                        elevation = ButtonDefaults.elevation(5.dp, 2.dp),
                        modifier = Modifier
                            .width(100.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Orange2,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = if (isTimerRunning) "PAUSE" else "RESUME",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(32.dp))
                    Button(
                        onClick = {
                            goToExercise()
                        },
                        shape = RoundedCornerShape(30.dp),
                        elevation = ButtonDefaults.elevation(5.dp, 2.dp),
                        modifier = Modifier
                            .width(100.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Orange1
                        )
                    ) {
                        Text(text = "SKIP", fontWeight = FontWeight.Bold)
                    }
                }
            }
            TextDescription(
                text = "Exercise: ${exerciseIndex + 1}/${workout.exercises.size}",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            val todo = when (exercise.type) {
                ExerciseType.CountAmount -> "x$amount"
                ExerciseType.CountAmountTwoSide -> "x$amount both sides"
                ExerciseType.CountDuration -> "00:$amount"
                ExerciseType.CountDurationTwoSide -> "00:$amount both sides"
            }
            Title(
                text = exercise.name + " $todo", color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.5f)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            ) {
                Image(
                    painter = painterResource(id = exercise.image),
                    contentDescription = exercise.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopEnd)
                        .size(30.dp),
                    onClick = { onExerciseDetailClick(exercise) },
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

        IconButton(
            onClick = popBackStack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back", tint = Color.White
            )
        }
    }
}


@Composable
fun CountAmountExercise(
    exerciseIndex: Int,
    workout: Workout,
    amount: Int,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    popBackStack: ()->Unit,
    onExerciseDetailClick: (Exercise) -> Unit
) {
    val exercise = workout.exercises[exerciseIndex]

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = exercise.image),
                contentDescription = exercise.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextName(
                    text = "Exercise: ${exerciseIndex + 1}/${workout.exercises.size}",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(30.dp),
                    onClick = { onExerciseDetailClick(exercise) },
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = exercise.name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = if (exercise.type == ExerciseType.CountAmount) "$amount" else "${amount.times(2)}",
                    fontSize = 50.sp, fontWeight = FontWeight.Bold
                )
                if (exercise.type == ExerciseType.CountAmountTwoSide) {
                    Text(
                        text = "$amount each sides",
                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onNextClick,
                    shape = RoundedCornerShape(30.dp),
                    elevation = ButtonDefaults.elevation(5.dp, 2.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                ) {
                    Text(text = "DONE", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = onPreviousClick,
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f),
                    enabled = exerciseIndex > 0
                ) {
                    Text(text = "PREVIOUS", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                TextButton(
                    onClick = onNextClick,
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f)
                ) {
                    Text(text = "NEXT", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        IconButton(
            onClick = popBackStack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back", tint = Orange1
            )
        }
    }
}


@Composable
fun CountDurationExercise(
    exerciseIndex: Int,
    workout: Workout,
    duration: Int,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    popBackStack: ()->Unit,
    onExerciseDetailClick: (Exercise) -> Unit
) {
    val exercise = workout.exercises[exerciseIndex]
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = exercise.image),
                contentDescription = exercise.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextName(
                    text = "Exercise: ${exerciseIndex + 1}/${workout.exercises.size}",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(30.dp),
                    onClick = { onExerciseDetailClick(exercise) },
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = exercise.name, fontSize = 30.sp, fontWeight = FontWeight.Bold)

                var isTimerRunning by remember {
                    mutableStateOf(true)
                }
                TimerCountdownAnalog(
                    totalTime = duration,
                    modifier = Modifier.size(180.dp),
                    isTimerRunning = isTimerRunning,
                    onFinished = onNextClick
                )

                if (exercise.type == ExerciseType.CountDurationTwoSide) {
                    Text(
                        text = "00:${duration} each sides",
                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        isTimerRunning = !isTimerRunning
                    },
                    shape = RoundedCornerShape(30.dp),
                    elevation = ButtonDefaults.elevation(5.dp, 2.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                ) {
                    Text(
                        text = if (isTimerRunning) "PAUSE" else "RESUME",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = onPreviousClick,
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f),
                    enabled = exerciseIndex > 0
                ) {
                    Text(text = "PREVIOUS", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                TextButton(
                    onClick = onNextClick,
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f)
                ) {
                    Text(text = "NEXT", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        IconButton(
            onClick = popBackStack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back", tint = Orange1
            )
        }
    }
}


@Composable
fun FinishScreen(
    workout: Workout,
    onFinished: ()->Unit,
    onExit: () -> Unit
) {
    LaunchedEffect(key1 = null){
        onFinished()
    }
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.trophy),
                contentDescription = "Congratulation",
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = "WELL DONE!",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = buildAnnotatedString {
                    append("You have finished ")
                    withStyle(
                        SpanStyle(color = Orange1, fontWeight = FontWeight.Bold)
                    ) {
                        append(workout.name)
                    }
                })
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Title(text = "Workout Statistic", modifier = Modifier.padding(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                Brush.linearGradient(
                                    listOf(Orange3, Orange1, Orange2),
                                    start = Offset(0f, 0f),
                                    end = Offset.Infinite
                                ),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = workout.exercises.size.toString(),
                            fontWeight = FontWeight.Black,
                            fontSize = 26.sp,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                    TextDescription(text = "Exercises")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                Brush.linearGradient(
                                    listOf(Orange3, Orange1, Orange2),
                                    start = Offset(0f, 0f),
                                    end = Offset.Infinite
                                ),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${workout.exercises.size *2}",
                            fontWeight = FontWeight.Black,
                            fontSize = 26.sp,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                    TextDescription(text = "Minutes")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                Brush.linearGradient(
                                    listOf(Orange3, Orange1, Orange2),
                                    start = Offset(0f, 0f),
                                    end = Offset.Infinite
                                ),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        var cal = 0
                        workout.exercises.forEach { ex ->
                            cal += when (ex.level) {
                                ExerciseLevel.Easy -> 1
                                ExerciseLevel.Medium -> 2
                                ExerciseLevel.Hard -> 3
                            }
                        }
                        Text(
                            text = cal.toString(),
                            fontWeight = FontWeight.Black,
                            fontSize = 26.sp,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                    TextDescription(text = "Calories")
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    onExit()
                },
                shape = RoundedCornerShape(30.dp),
                elevation = ButtonDefaults.elevation(5.dp, 2.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "DONE", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

    BackHandler {
        onExit()
    }
}


@Composable
fun TimerCountdownAnalog(
    totalTime: Int,
    modifier: Modifier = Modifier,
    isTimerRunning: Boolean,
    onFinished: () -> Unit
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var value by remember {
        mutableStateOf(1f)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0 && isTimerRunning) {
            delay(1000L)
            currentTime -= 1
            value = currentTime / totalTime.toFloat()
        }
        if (currentTime <= 0){
            onFinished()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(10.dp)
            .onSizeChanged {
                size = it
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArc(
                color = Color.Gray,
                startAngle = 90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(10.dp.toPx()),
                size = Size(size.width.toFloat(), size.height.toFloat())
            )
            drawArc(
                color = Orange1,
                startAngle = 90f,
                sweepAngle = 360f * value,
                useCenter = false,
                style = Stroke(10.dp.toPx()),
                size = Size(size.width.toFloat(), size.height.toFloat())
            )

            val radius = (value * 360f + 90f) * PI.toFloat() / 180
            val x = size.center.x + (size.center.x * cos(radius))
            val y = size.center.y + (size.center.y * sin(radius))
            drawCircle(
                color = Orange2,
                style = Fill,
                radius = 10.dp.toPx(),
                center = Offset(x, y)
            )
        }
        val timeDisplay = String.format("%02d", currentTime / 60) + ":" +
                String.format("%02d", currentTime % 60)
        Text(
            text = timeDisplay,
            fontSize = 30.sp,
            color = Orange2,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TimerCountdownDigital(
    totalTime: Int,
    modifier: Modifier = Modifier,
    isTimerRunning: Boolean,
    textColor: Color,
    onFinished: () -> Unit
) {
    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0 && isTimerRunning) {
            delay(1000L)
            currentTime -= 1
        }
        if (currentTime <= 0) {
            onFinished()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val timeDisplay = String.format("%02d", currentTime / 60) + ":" +
                String.format("%02d", currentTime % 60)
        Text(
            text = timeDisplay,
            fontSize = 50.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}

//@Preview
//@Composable
//fun PreviewTimer() {
//    TimerCountdownAnalog(totalTime = 100, modifier = Modifier.size(250.dp), false)
//}