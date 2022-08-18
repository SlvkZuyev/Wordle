package com.slvkdev.ruwordle.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.slvkdev.ruwordle.notifications.MyAlarmManager

class SampleBootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            MyAlarmManager.setup(context)
        }
    }

}
