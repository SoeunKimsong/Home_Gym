package com.example.homegym.presentation.workout

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.homegym.R
import com.example.homegym.data.exercises.Exercise
import com.example.homegym.presentation.navigation.Screen
import com.example.homegym.presentation.screen_items.ExerciseDetailDialog
import com.example.homegym.presentation.screen_items.ExerciseItem
import com.example.homegym.presentation.screen_items.TextName
import com.example.homegym.presentation.screen_items.Title
import com.example.homegym.ui.theme.HomeGymTheme
import com.example.homegym.ui.theme.Orange1
import com.example.homegym.ui.theme.Orange2

@Composable
fun WorkoutScreen(
    workoutId: Int,
    navController: NavController,
    viewModel: WorkoutViewModel = hiltViewModel()
) {

    val workout = viewModel.getWorkoutById(workoutId)

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

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
        ) {
            Image(
                painter = painterResource(id = workout.image ?: R.drawable.workout),
                contentDescription = workout.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(.3f))
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
            ) {
                Title(text = workout.name, color = Color.White)
                TextName(text = "${workout.exercises.size} Exercises", color = Color.White)
            }
            IconButton(
                onClick = { navController.popBackStack() },
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

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            workout.exercises.forEach { exercise ->
                ExerciseItem(exercise = exercise, onExerciseDetailClick)
                Divider()
            }
            Divider(color = Orange1, thickness = 10.dp)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.Exercise.route + "?workout_id=$workoutId")
                },
                shape = RoundedCornerShape(30.dp),
                elevation = ButtonDefaults.elevation(5.dp, 2.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "START", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
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
fun PreviewWorkout() {
    HomeGymTheme {
        Scaffold {
            WorkoutScreen(2, rememberNavController())
        }
    }
}