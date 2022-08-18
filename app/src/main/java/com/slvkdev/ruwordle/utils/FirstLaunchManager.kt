package com.slvkdev.ruwordle.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

class FirstLaunchManager(private val context: Context) {
    private val KEY_FIRST_LAUNCH = "is_first_launch"

    suspend fun isFirstLaunch(): Boolean {
        val prefs = context.getSharedPreferences("first_launch_prefs", MODE_PRIVATE)
        return prefs.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    suspend fun registerFirstLaunch(){
        val prefs = context.getSharedPreferences("first_launch_prefs", MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }
}