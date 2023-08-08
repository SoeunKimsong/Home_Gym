package com.example.homegym.presentation.screen_items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.homegym.R
import com.example.homegym.data.exercises.Exercise
import com.example.homegym.data.exercises.ExerciseLevel
import com.example.homegym.data.exercises.ExerciseType
import com.example.homegym.data.exercises.Workout
import com.example.homegym.ui.theme.Orange1
import com.example.homegym.ui.theme.Orange2
import com.example.homegym.ui.theme.Orange3

@Composable
fun ExerciseItem(
    exercise: Exercise,
    onExerciseDetailClick: (Exercise)->Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = exercise.image),
            contentDescription = exercise.name,
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            TextName(text = exercise.name)
            val todo = when (exercise.type) {
                ExerciseType.CountAmount -> {
                    when (exercise.level) {
                        ExerciseLevel.Easy -> "x16"
                        ExerciseLevel.Medium -> "x12"
                        ExerciseLevel.Hard -> "x10"
                    }
                }
                ExerciseType.CountAmountTwoSide -> {
                    when (exercise.level) {
                        ExerciseLevel.Easy -> "x14"
                        ExerciseLevel.Medium -> "x10"
                        ExerciseLevel.Hard -> "x8"
                    }
                }
                ExerciseType.CountDuration -> {
                    when (exercise.level) {
                        ExerciseLevel.Easy -> "1:00"
                        ExerciseLevel.Medium -> "00:50"
                        ExerciseLevel.Hard -> "00:30"
                    }
                }
                ExerciseType.CountDurationTwoSide -> {
                    when (exercise.level) {
                        ExerciseLevel.Easy -> "00:60"
                        ExerciseLevel.Medium -> "00:30"
                        ExerciseLevel.Hard -> "00:20"
                    }
                }
            }

            TextCaption(text = todo, modifier = Modifier.padding(top = 8.dp))

        }
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
}


@Composable
fun ExercisesLazyY(
    exercises: List<Exercise>,
    onExerciseClick: (Exercise)->Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            Spacer(modifier = Modifier.width(16.dp))
        }
        items(exercises.size) {
            val exercise = exercises[it]
            Column(modifier = Modifier.clickable { onExerciseClick(exercise) }) {
                Image(
                    painter = painterResource(id = exercise.image),
                    contentDescription = exercise.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(180.dp, 120.dp)
                        .padding(vertical = 8.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                )
                Text(
                    text = exercise.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
    }

}


@Composable
fun ExerciseDetailDialog(
    exercise: Exercise,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.surface)
        )
        {
            Image(
                painter = painterResource(id = exercise.image),
                contentDescription = exercise.name,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Title(text = exercise.name, modifier = Modifier.padding(24.dp))
            TextCaption(
                text = exercise.instruction,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            TextCaption(
                text = "Level : ${exercise.level}",
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
            )
            Divider(modifier = Modifier.padding(horizontal = 24.dp))
            TextCaption(
                text = "Good for : ${exercise.bodyPartBestFor.name}",
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
            )
            Divider(modifier = Modifier.padding(horizontal = 24.dp))
            exercise.tip?.let {
                Spacer(modifier = Modifier.height(24.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextCaption(
                        text = "TIP : ",
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    TextCaption(
                        text = exercise.tip,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(onClick = onDismiss, modifier = Modifier
                .padding(24.dp)
                .align(Alignment.End)) {
                Text(text = "CLOSE", color = Color.Blue)
            }
        }
    }
}


@Composable
fun WorkoutItem(
    workout: Workout,
    onclickWorkout: (Workout) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(120.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Orange1, Orange2)
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                onclickWorkout(workout)
            }
    ) {
        Image(
            painter = painterResource(id = workout.image ?: R.drawable.workout),
            contentDescription = workout.name,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent, Color.Black.copy(alpha = .4f)
                        )
                    )
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Title(text = workout.name, color = MaterialTheme.colors.onPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${workout.exercises.size} Exercises",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onPrimary
            )
//            Spacer(modifier = Modifier.weight(1f))
//            Text(
//                text = "Last time: 28/June",
//                fontSize = 14.sp,
//                color = MaterialTheme.colors.onPrimary,
//                modifier = Modifier
//                    .align(Alignment.Start)
//                    .background(
//                        MaterialTheme.colors.primary,
//                        RoundedCornerShape(10.dp)
//                    )
//                    .padding(vertical = 4.dp, horizontal = 8.dp)
//            )
        }
    }
}


@Composable
fun WorkoutItem2(
    workout: Workout,
    onclickWorkout: (Workout) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(120.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Orange1, Orange2)
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                onclickWorkout(workout)
            }
    ) {
        Image(
            painter = painterResource(id = workout.image ?: R.drawable.workout),
            contentDescription = workout.name,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(alpha = .2f)
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Title(text = workout.name, color = MaterialTheme.colors.onPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${workout.exercises.size} Exercises",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}


@Composable
fun ComingSoonWorkoutItem(
    name: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(120.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Orange1, Orange2)
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.workout),
            contentDescription = "Workout",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent, Color.Black.copy(alpha = .4f)
                        )
                    )
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Title(text = name, color = MaterialTheme.colors.onPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Coming soon",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}


@Composable
fun WorkoutItemsLazyXGrid2(
    workouts: List<Workout>,
    modifier: Modifier = Modifier,
    onclickWorkout: (Workout) -> Unit
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(workouts.size) {
            val workout = workouts[it]
            Row(
                modifier = Modifier
                    .clickable {
                        onclickWorkout(workout)
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .width(300.dp)

            ) {
                Image(
                    painter = painterResource(id = workout.icon ?: R.drawable.workout),
                    contentDescription = workout.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .shadow(
                            5.dp,
                            RoundedCornerShape(20.dp)
                        )
                )
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp)
                ) {
                    TextName(text = workout.name)
                    TextCaption(text = "${workout.exercises.size} Exercises", color = Color.Gray)
                    Spacer(modifier = Modifier.weight(1f))
                    Divider()
                }
            }
        }
    }
}


@Composable
fun WorkoutItemsLazyXBox(
    workouts: List<Workout>,
    modifier: Modifier = Modifier,
    onclickWorkout: (Workout) -> Unit
) {
    LazyRow(modifier = modifier) {
        items(workouts.size) {
            val workout = workouts[it]
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .padding(16.dp)
                    .shadow(
                        10.dp,
                        RoundedCornerShape(30.dp)
                    )
                    .background(
                        brush = Brush.linearGradient(
                            listOf(Orange1, Orange3),
                            start = Offset(0f, 180f),
                            end = Offset(180f, 0f)
                        ),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable {
                        onclickWorkout(workout)
                    }
                    .padding(16.dp)

            ) {
                TextName(
                    text = workout.name,
                    modifier = Modifier.align(Alignment.BottomStart),
                    color = MaterialTheme.colors.onPrimary
                )
                Icon(
                    painter = painterResource(id = workout.icon ?: R.drawable.ic_dumbbell),
                    contentDescription = workout.name,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(50.dp),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}


@Composable
fun StopExerciseDialog(
    onOK: ()->Unit,
    onDismiss: ()->Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.surface)
                .padding(24.dp)
        )
        {
            Title(text = "Stop this Workout")
            Spacer(modifier = Modifier.height(24.dp))
            TextDescription(text = "Are you sure you want to stop this workout and lose this progress?")
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss,
                ){
                    Text(text = "CANCEL", color = Color.Red)
                }
                TextButton(onClick = onOK,
                ){
                    Text(text = "OK", color = Color.Blue)
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewDialog() {
    StopExerciseDialog(
        onOK = {  },
        onDismiss = {}
    ) 
}