package com.slvkdev.ruwordle.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.util.Log
import com.slvkdev.ruwordle.utils.GetNotificationTimestamp
import com.slvkdev.ruwordle.utils.GetSecondsUntilMidnight
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit



class MyAlarmManager {
    companion object {
        private const val notificationInterval = AlarmManager.INTERVAL_DAY

        fun setup(context: Context) {
            val notificationTime = GetNotificationTimestamp()
            val intent = Intent(context, NotificationReceiver::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(context, 0, intent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                notificationTime,
                notificationInterval,
                pendingIntent
            )
        }
    }
}
