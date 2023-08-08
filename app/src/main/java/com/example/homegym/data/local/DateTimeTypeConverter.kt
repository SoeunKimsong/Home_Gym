package com.example.homegym.data.local

import androidx.room.TypeConverter
import java.time.LocalDateTime

class DateTimeTypeConverter {

    @TypeConverter
    fun dateTimeToString(value: LocalDateTime?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun stringToDateTime(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

}