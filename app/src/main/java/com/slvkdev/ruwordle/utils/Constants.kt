package com.slvkdev.ruwordle.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey

val KEY_LAST_ATTEMPT_TIMESTAMP = longPreferencesKey("last_attempt_timestamp")
val KEY_IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
val USER_INFO = "user_info"
val MILLIS_IN_DAY = 86400000
val DICTIONARY_DATABASE_PATH = "dictionary.db"