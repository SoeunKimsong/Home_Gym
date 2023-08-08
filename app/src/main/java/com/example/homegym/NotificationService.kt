package com.example.homegym

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.homegym.util.checkNotificationPermission
import com.example.homegym.util.sendNotification
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationService: Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.e("Notification Service", "Sending Notification")
        val isNotificationAllow = checkNotificationPermission(this)
        if(isNotificationAllow){
            sendNotification(
                title = "Time to Workout",
                description = "Come on. It's time to burn some Calorie and obtain your perfect body shape",
                context = this
            )
        }
        stopSelf()

        return super.onStartCommand(intent, flags, startId)
    }
}