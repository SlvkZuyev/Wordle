package com.slvkdev.wordle.utils

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

const val WORDLE_FIELD_SNAPSHOT = "wordle_field_snapshot"
val KEY_REQUESTED_WORD = stringPreferencesKey("requested_word")
val KEY_INSERTED_WORDS = stringPreferencesKey("inserted_words")
val KEY_SNAPSHOT_TIMESTAMP = longPreferencesKey("snapshot_timestamp")