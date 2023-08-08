package com.example.homegym.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homegym.R
import com.example.homegym.data.exercises.Exercise
import com.example.homegym.data.exercises.Workout
import com.example.homegym.domain.model.History
import com.example.homegym.presentation.screen_items.ComingSoonWorkoutItem
import com.example.homegym.presentation.screen_items.ExercisesLazyY
import com.example.homegym.presentation.screen_items.TextCaption
import com.example.homegym.presentation.screen_items.Title
import com.example.homegym.presentation.screen_items.WorkoutItem
import com.example.homegym.presentation.screen_items.WorkoutItemsLazyXBox
import com.example.homegym.ui.theme.Orange1
import com.example.homegym.ui.theme.Orange2
import java.time.LocalDate

@Composable
fun WorkoutStrike(
    workoutStrike: Int,
    lastWeekHistory: List<List<History>>
) {
    val primaryColor = MaterialTheme.colors.primary
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .drawWithCache {
                onDrawBehind {
                    drawRoundRect(
                        color = primaryColor,
                        topLeft = Offset(0f, -100f),
                        size = Size(this.size.width, 350f),
                        cornerRadius = CornerRadius(50f, 50f)
                    )
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Workout Strike : $workoutStrike",
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            val burnLevel = when {
                workoutStrike > 20 -> 5
                workoutStrike > 15 -> 4
                workoutStrike > 10 -> 3
                workoutStrike > 5 -> 2
                workoutStrike > 0 -> 1
                else -> 0
            }
            repeat(burnLevel) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dumbbell),
                    contentDescription = "icon",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp),
        ) {
            Text(
                text = "Week Strike",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                var day = LocalDate.now().minusDays(6)
                repeat(7) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                color = if (lastWeekHistory[6-it].isNotEmpty()) Orange1 else MaterialTheme.colors.background ,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day.dayOfMonth.toString(),
                            color = if (lastWeekHistory[6-it].isNotEmpty()) MaterialTheme.colors.onPrimary else Color.DarkGray,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    day = day.plusDays(1)
                }
            }
        }

    }
}


@Composable
fun FeaturedExercise(
    exercise: Exercise,
    onExerciseClick: (Exercise) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                )

        ) {
            Image(
                painter = painterResource(id = exercise.image),
                contentDescription = exercise.name,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .align(Alignment.Center)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "star",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colors.primaryVariant
                )
                Text(
                    text = " Featured Exercise",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colors.primaryVariant,
                )

            }
            Text(
                text = exercise.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
                    .size(30.dp),
                onClick = { onExerciseClick(exercise) },
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
}


@Composable
fun RecentWorkout(
    workout: Workout,
    onclickWorkout: (Workout) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onclickWorkout(workout) }
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(120.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Orange1, Orange2)
                ),
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Image(
            painter = painterResource(id = workout.image ?: R.drawable.workout),
            contentDescription = workout.name,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    color = Color.Green
                )
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text(
                text = "Recent Workout",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = workout.name,
                modifier = Modifier
                    .weight(1f),
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Try it ->",
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.onPrimary,
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.End
            )
        }

    }
}


@Composable
fun Challenges(
    workouts: List<Workout>,
    onclickWorkout: (Workout)->Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Title(
            text = "Challenges",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        WorkoutItemsLazyXBox(workouts = workouts, onclickWorkout = onclickWorkout)
    }
}

@Composable
fun AllBodyPartsWorkout(
    workouts: List<Workout>,
    onclickWorkout: (Workout) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Title(
            text = "Body Parts Workout",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        workouts.forEach {
            WorkoutItem(it, onclickWorkout)
        }
        ComingSoonWorkoutItem(name = "Legs Workout")
        ComingSoonWorkoutItem(name = "Shoulder Workout")
    }
}


@Composable
fun WarmupExercises(
    exercises: List<Exercise>,
    onExerciseClick: (Exercise) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Title(
            text = "Warmup Exercises",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        ExercisesLazyY(exercises = exercises, onExerciseClick)
    }
}


@Composable
fun DiscoverMore(
    changeScreen: ()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(100.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                brush = Brush.horizontalGradient(
                    listOf(Orange1, Orange2)
                ),
                shape = RoundedCornerShape(20.dp)
            )

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Title(text = "Discover", color = MaterialTheme.colors.onPrimary)
            TextCaption(text = "More Workouts", color = MaterialTheme.colors.onPrimary)
        }
        Button(
            onClick = changeScreen,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = MaterialTheme.colors.primary
            ),
            modifier = Modifier
                .padding(16.dp)
                .width(80.dp)
                .align(Alignment.BottomEnd)

        ) {
            Text(
                text = "GO!",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
