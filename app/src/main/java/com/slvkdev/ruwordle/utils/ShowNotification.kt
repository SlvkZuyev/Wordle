package com.slvkdev.ruwordle.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ShowNotification @Inject constructor(@ApplicationContext private val context: Context) {
    operator fun invoke(
        smallIconResId: Int,
        largeIconResId: Int,
        onClickIntent: PendingIntent,
        notificationTitle: String,
        notificationMessage: String,
        notificationId: Int
    ){

        val builder = NotificationCompat
            .Builder(context, NOTIFICATIONS_CHANNEL_ID)
            .setSmallIcon(smallIconResId)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, largeIconResId))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(onClickIntent)
            .setAutoCancel(true)

        createNotificationChannel()

        builder
            .setContentTitle(notificationTitle)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(notificationMessage)
            )

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, builder.build())
        }

    }

    private fun createNotificationChannel() {
        val name = NOTIFICATIONS_CHANNEL_NAME
        val descriptionText = NOTIFICATIONS_CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(NOTIFICATIONS_CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}