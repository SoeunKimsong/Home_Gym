package com.example.homegym

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.homegym.domain.repository.SettingsRepository
import com.example.homegym.util.scheduleDailyNotification
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class NotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var repo: SettingsRepository

    override fun onReceive(context: Context, intent: Intent?) {

        val service = Intent(context, NotificationService::class.java)
        context.startService(service)
        Log.e("Notification Receiver", "Start Service")


        // send tomorrow
        CoroutineScope(Dispatchers.IO).launch {
            repo.getNotificationRemindTime().collectLatest { time ->
                time.let {
                    context.let { cont ->
                        val sendTime = Calendar.Builder()
                            .setInstant(it)
                            .build()
                        sendTime.add(Calendar.DATE, 1)

                        val receiver = Intent(cont, NotificationReceiver::class.java)
                        scheduleDailyNotification(
                            context = cont,
                            time = sendTime,
                            receiver = receiver
                        )

                        repo.setNotificationRemindTime(sendTime)
                    }
                }
                Log.e("Next Notification", "Setting Next Notification")
                cancel()
            }

        }

    }
}