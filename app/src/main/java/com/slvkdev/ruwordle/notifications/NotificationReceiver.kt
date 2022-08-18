package com.slvkdev.ruwordle.notifications

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.slvkdev.ruwordle.MainActivity
import com.slvkdev.ruwordle.R
import com.slvkdev.ruwordle.data.UserInfoRepository
import com.slvkdev.ruwordle.utils.ShowNotification
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class NotificationReceiver : BroadcastReceiver() {
    lateinit var context: Context

    @Inject
    lateinit var showNotification: ShowNotification

    @Inject
    lateinit var userInfoRepository: UserInfoRepository

    override fun onReceive(context: Context?, p1: Intent?) {
        Log.d("SlvkLog", "Receiver recived")
        this.context = context!!
        showNotification()
    }


    private fun showNotification() {
        showNotification(
            smallIconResId = R.drawable.notification_icon_small,
            largeIconResId = R.drawable.notification_icon_big,
            onClickIntent = getOnNotificationClickIntent(),
            notificationTitle = getNotificationTitle(),
            notificationMessage = getNotificationMessage(),
            notificationId = getNotificationId()
        )
    }

    private fun getOnNotificationClickIntent(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        return PendingIntent.getActivity(
            context, 0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun getNotificationTitle(): String {
        return "Новое слово доступно"
    }

    private fun getNotificationMessage(): String {
        val phrases = listOf(
            "Но сможешь ли ты его откадать?",
        )
        return phrases.random()
    }

    private fun getNotificationId(): Int {
        return Random.nextInt()
    }

}