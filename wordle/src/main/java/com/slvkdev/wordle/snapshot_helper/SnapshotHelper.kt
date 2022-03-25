package com.slvkdev.wordle.snapshot_helper

import android.content.Context
import android.text.format.DateUtils
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.slvkdev.wordle.utils.KEY_INSERTED_WORDS
import com.slvkdev.wordle.utils.KEY_REQUESTED_WORD
import com.slvkdev.wordle.utils.KEY_SNAPSHOT_TIMESTAMP
import com.slvkdev.wordle.utils.WORDLE_FIELD_SNAPSHOT
import com.slvkdev.wordle.field.WordleFieldGrid
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val Context.dataStore by preferencesDataStore(WORDLE_FIELD_SNAPSHOT)

class SnapshotHelper(context: Context) {
    private val snapshotDatastore = context.dataStore

    suspend fun saveSnapshot(snapshot: WordleSnapshot) {
        snapshotDatastore.edit { preferences ->
            preferences[KEY_REQUESTED_WORD] = snapshot.requestedWord
            preferences[KEY_INSERTED_WORDS] = snapshot.insertedWords.toJson()
            preferences[KEY_SNAPSHOT_TIMESTAMP] = snapshot.timestamp
        }
        Log.d("SnaphotHelper", "Snapshot saved for word(s): ${snapshot.requestedWord}")
    }

    suspend fun loadTodaySnapshot(): WordleSnapshot? {
        return snapshotDatastore.data.map { preferences ->
            val requestedWord = preferences[KEY_REQUESTED_WORD] ?: ""
            val insertedWords = preferences[KEY_INSERTED_WORDS] ?: ""
            val snapshotTimestamp = preferences[KEY_SNAPSHOT_TIMESTAMP] ?: 0

            Log.d("SnaphotHelper", "Snapshot loaded with word(s): ${insertedWords}")
            if(DateUtils.isToday(snapshotTimestamp)){
                WordleSnapshot(
                    requestedWord = requestedWord,
                    insertedWords = Json.decodeFromString(insertedWords),
                    timestamp = snapshotTimestamp
                )
            } else {
                null
            }
        }.first()
    }

}