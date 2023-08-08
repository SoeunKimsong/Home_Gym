package com.example.homegym.util

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.homegym.MainActivity
import com.example.homegym.R
import java.util.Calendar

const val NOTIFICATION_CHANNEL = "123"
const val REQUEST_CODE_NOTIFICATION = 123

fun sendNotification(
    title: String,
    description: String,
    context: Context
){
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val intent = Intent(context, MainActivity::class.java)
        .setAction(Intent.ACTION_MAIN)
        .addCategory(Intent.CATEGORY_LAUNCHER)
    val pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE_NOTIFICATION, intent, PendingIntent.FLAG_IMMUTABLE)

    val notification = NotificationCompat.Builder(
        context,
        NOTIFICATION_CHANNEL
    )
        .setSmallIcon(R.drawable.logo)
        .setContentTitle(title)
        .setContentText(description)
        .setAutoCancel(true)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(pendingIntent)

    notificationManager.notify(1, notification.build())
}


fun checkNotificationPermission(
    context: Context
): Boolean{
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED)
    } else {
        NotificationManagerCompat.from(context).areNotificationsEnabled()
    }
}


fun scheduleDailyNotification(
    context: Context,
    time: Calendar,
    receiver: Intent
){
    val pendingIntent = PendingIntent.getBroadcast(
        context, REQUEST_CODE_NOTIFICATION, receiver,
        PendingIntent.FLAG_IMMUTABLE
    )
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.setExact(
        AlarmManager.RTC_WAKEUP,
        time.timeInMillis,
        pendingIntent
    )
}

fun cancelScheduleDailyNotification(
    context: Context,
    receiver: Intent
){
    val pendingIntent = PendingIntent.getBroadcast(
        context, REQUEST_CODE_NOTIFICATION, receiver,
        PendingIntent.FLAG_IMMUTABLE
    )
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(pendingIntent)
}