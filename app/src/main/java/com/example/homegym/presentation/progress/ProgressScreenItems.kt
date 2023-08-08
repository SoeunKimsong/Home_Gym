package com.example.homegym.presentation.progress

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homegym.R
import com.example.homegym.data.exercises.ExerciseLevel
import com.example.homegym.data.exercises.Workout
import com.example.homegym.domain.model.History
import com.example.homegym.presentation.screen_items.TextCaption
import com.example.homegym.presentation.screen_items.TextDescription
import com.example.homegym.presentation.screen_items.TextName
import com.example.homegym.presentation.screen_items.Title
import com.example.homegym.ui.theme.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter

@Composable
fun WorkoutStrike(
    dayStrike: Int,
    workoutStrike: Int,
    timeSpent: String
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(10.dp, RoundedCornerShape(30.dp))
            .background(Color.White, RoundedCornerShape(30.dp))
    ) {
        TextName(
            text = "Workout Strike",
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
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
                    text = buildAnnotatedString {
                        append(dayStrike.toString())
                        withStyle(SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal)) {
                            append(" days")
                        }
                    },
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    color = MaterialTheme.colors.onPrimary
                )
            }

            Column {
                TextCaption(
                    text = "Workouts Strike: $workoutStrike",
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
                TextCaption(
                    text = "Time Spent: $timeSpent",
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextCaption(
                        text = "Burn: ",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    val burnLevel = when{
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
                            tint = MaterialTheme.colors.primaryVariant,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

            }

        }
    }
}


@Composable
fun WorkoutCalendar(
    map: Map<LocalDate, List<History>>,
    monthYearDisplay: LocalDate,
    selectedDate: LocalDate,
    onForwardMonth: ()->Unit,
    onBackwardMonth: ()->Unit,
    onSelectDate: (Int)->Unit
) {
    Title(
        text = "Calendar Tracker",
        modifier = Modifier.padding(16.dp)
    )
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = onBackwardMonth,
            modifier = Modifier
                .size(40.dp)
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .background(Orange1, RoundedCornerShape(10.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_goleft),
                contentDescription = "go left",
                tint = MaterialTheme.colors.onPrimary
            )
        }

        TextName(text = "${monthYearDisplay.month} ${monthYearDisplay.year}")

        val enableForwardButton = monthYearDisplay.month != LocalDate.now().month
        IconButton(
            onClick = onForwardMonth,
            modifier = Modifier
                .size(40.dp)
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .background(
                    if(enableForwardButton) Orange1 else Orange3,
                    RoundedCornerShape(10.dp)
                ),
            enabled = enableForwardButton
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_goright),
                contentDescription = "go right",
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier
            .fillMaxWidth()
            .height(440.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp, alignment = Alignment.CenterHorizontally)
    ) {

        // display day name
        DayOfWeek.values().forEach {
            item {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = it.name.take(3),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // skip to show correct day starting from day 1
        var startDay = 1
        while (startDay != monthYearDisplay.withDayOfMonth(1).dayOfWeek.value){
            item { Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .size(30.dp),
            ) }
            startDay++
        }

        // display date
        val days = Month.of(monthYearDisplay.monthValue).length(monthYearDisplay.year%4 == 0)
        items(days) {
            val day = it + 1
            val isToday = day == LocalDate.now().dayOfMonth && monthYearDisplay == LocalDate.now()
            val hasHistoryThisDay = !map[monthYearDisplay.withDayOfMonth(day)].isNullOrEmpty()
            Box(
                modifier = Modifier
                    .clickable {
                        onSelectDate(day)
                    }
                    .aspectRatio(1f)
                    .size(30.dp)
                    .background(
                        color = if (isToday) Orange2
                        else if (hasHistoryThisDay) Orange1
                        else White,
                        shape = CircleShape
                    )
                    .border(
                        BorderStroke(4.dp, if (day == selectedDate.dayOfMonth) Orange2 else White),
                        shape = CircleShape
                    )

                ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day.toString(),
                    color = if (isToday || hasHistoryThisDay) Color.White
                    else Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(6.dp))

        }

    }

}


@Composable
fun WorkoutHistory(
    history: List<History>,
    date: LocalDate,
    findWorkoutById: (Int) -> Workout
) {
    if (history.isNotEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextName(text = "${date.dayOfMonth} ${date.month}")
            TextDescription(text = "${history.size} Workouts")
        }
        Divider()
        Column {
            history.forEach { item ->
                val workout = findWorkoutById(item.workoutId)
                Row(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = workout.image ?: R.drawable.workout),
                        contentDescription = workout.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(80.dp)
                            .background(Color.White, RoundedCornerShape(10.dp))
                    )
                    Column {
                        TextName(text = workout.name)
                        val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                        TextCaption(text = item.dateTime.format(formatter))
                    }
                }
                Divider()
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            TextName(text = "${date.dayOfMonth} ${date.month}")
            Title(
                text = "No History",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            Divider()
        }
    }
}


@OptIn(ExperimentalTextApi::class)
@Composable
fun CalorieBurnGraph(
    currentDay: LocalDateTime,
    history: List<List<History>>,
    findWorkoutById: (Int) -> Workout
) {
    Title(
        text = "Calorie Burner Graph",
        modifier = Modifier.padding(16.dp)
    )

    var day = currentDay.plusDays(1)
    val xValues = List(7) {
        day = day.minusDays(1)
        day.dayOfMonth.toString()
    }.reversed()
    val yValues = List(history.size) {
        var cal = 0
        history[it].forEach { item ->
            findWorkoutById(item.workoutId).exercises.forEach { ex ->
                cal += when (ex.level) {
                    ExerciseLevel.Easy -> 1
                    ExerciseLevel.Medium -> 2
                    ExerciseLevel.Hard -> 3
                }
            }
        }
        cal
    }.reversed()

    var max = yValues.max() + (yValues.max() / 5)
    if (max % 10 != 0) {
        max += (10 - (max % 10))
    }
    val min = 0
    val yStep = max / 5
    val textMeasurer = rememberTextMeasurer()
    val bottomPadding = 50f
    val startX = 100f
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(16.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            for (i in 0..5) {
                drawText(
                    textMeasurer = textMeasurer,
                    text = "${min + (yStep * i)}",
                    topLeft = Offset(
                        0f,
                        this.size.height - ((this.size.height / 6) * (i + 1)) - 30f
                    ),
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                )
                drawLine(
                    Color.Gray,
                    start = Offset(startX, this.size.height - ((this.size.height / 6) * (i + 1))),
                    end = Offset(
                        this.size.width,
                        this.size.height - ((this.size.height / 6) * (i + 1))
                    )
                )
            }

            // text under the graph
            val xStep = (this.size.width - startX) / xValues.size
            xValues.forEachIndexed { i, value ->
                drawText(
                    textMeasurer = textMeasurer,
                    text = value,
                    topLeft = Offset(startX + (xStep * i) - 5.dp.toPx(), this.size.height - bottomPadding)
                )
            }

            // graph
            val graphPath = generatePath(yValues, max, this.size)
            drawPath(
                graphPath,
                color = Orange1,
                style = Stroke(5f)
            )

            val canvasHeight = size.height - (size.height / 6)
            // shadow under the graph line
            val shadowPath = android.graphics.Path(graphPath.asAndroidPath())
                .asComposePath()
                .apply {
                    lineTo(startX + xStep * 6, canvasHeight)
                    lineTo(startX, canvasHeight)
                    close()
                }
            drawPath(
                shadowPath,
                brush = Brush.verticalGradient(
                    listOf(Orange3, Color.Transparent)
                ),
                style = Fill
            )

        }
    }
}

private fun generatePath(values: List<Int>, max: Int, size: Size): Path {
    val startX = 100f
    val startBottom = (size.height / 6)
    val xStep = (size.width - startX) / values.size
    val canvasHeight = size.height - startBottom
    val path = Path()
    repeat(values.size) { i ->
        val x1 = startX + (xStep * i)
        val y1 = canvasHeight - (canvasHeight * values[i] / max)
        val x2 = if (i < values.size - 1) startX + (xStep * (i + 1)) else x1
        val y2 =
            if (i < values.size - 1) canvasHeight - (canvasHeight * values[i + 1] / max) else y1

        if (i == 0) path.moveTo(x1, y1)
        path.cubicTo(
            (x1 + x2) / 2, y1,
            (x1 + x2) / 2, y2,
            x2, y2
        )
    }

    return path
}