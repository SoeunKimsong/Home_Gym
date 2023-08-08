package com.example.homegym.presentation.discover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homegym.data.exercises.Exercise
import com.example.homegym.data.exercises.Workout
import com.example.homegym.presentation.screen_items.ExercisesLazyY
import com.example.homegym.presentation.screen_items.Title
import com.example.homegym.presentation.screen_items.WorkoutItem
import com.example.homegym.presentation.screen_items.WorkoutItem2
import com.example.homegym.presentation.screen_items.WorkoutItemsLazyXBox
import com.example.homegym.presentation.screen_items.WorkoutItemsLazyXGrid2

@Composable
fun BurnerWorkout(
    workouts: List<Workout>,
    onclickWorkout: (Workout)->Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Title(
            text = "Burner Workout",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        workouts.forEach{workout ->
            WorkoutItem(
                workout = workout,
                onclickWorkout = onclickWorkout
            )
        }
    }
}

@Composable
fun FastWorkout(
    workouts: List<Workout>,
    onclickWorkout: (Workout)->Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Title(
            text = "Fast Workout",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        WorkoutItemsLazyXGrid2(
            workouts = workouts,
            modifier = Modifier
                .fillMaxWidth()
                .height(232.dp),
            onclickWorkout
        )
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
fun QuarantineWorkout(
    workouts: List<Workout>,
    onclickWorkout: (Workout)->Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Title(
            text = "Quarantine Workout",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        workouts.forEach { workout ->
            WorkoutItem2(workout = workout, onclickWorkout = onclickWorkout)
        }
    }
}


@Composable
fun AfterWorkoutStretch(
    exercises: List<Exercise>,
    onExerciseClick: (Exercise)->Unit
){
    Column(modifier = Modifier.fillMaxWidth()) {
        Title(
            text = "After Workout Stretch",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        ExercisesLazyY(exercises = exercises, onExerciseClick)
    }
}
