package com.example.homegym.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun formatHourMinute(calendar: Calendar): String{
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return formatter.format(calendar.time)
}