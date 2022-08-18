package com.slvkdev.ruwordle.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey

val KEY_LAST_ATTEMPT_TIMESTAMP = longPreferencesKey("last_attempt_timestamp")
val KEY_IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
const val USER_INFO = "user_info"
const val MILLIS_IN_DAY = 86400000
const val MILLIS_IN_HOUR = 3600000
const val DICTIONARY_DATABASE_PATH = "dictionary.db"
const val NOTIFICATIONS_CHANNEL_ID = "wordle_channel_id"
const val NOTIFICATIONS_CHANNEL_NAME = "wordle_channel_name"
const val NOTIFICATIONS_CHANNEL_DESCRIPTION = "wordle_channel_description"