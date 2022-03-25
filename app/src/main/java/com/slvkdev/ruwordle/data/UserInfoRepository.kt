package com.slvkdev.ruwordle.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.slvkdev.ruwordle.utils.KEY_IS_FIRST_LAUNCH
import com.slvkdev.ruwordle.utils.KEY_LAST_ATTEMPT_TIMESTAMP
import com.slvkdev.ruwordle.utils.USER_INFO
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(USER_INFO)
class UserInfoRepository @Inject constructor(@ApplicationContext appContext: Context) {

    private val userInfoDatastore = appContext.dataStore
    suspend fun getLastAttemptTimestamp(): Flow<Long> {
        return userInfoDatastore.data.map { preferences ->
            preferences[KEY_LAST_ATTEMPT_TIMESTAMP] ?: 0
        }
    }

    suspend fun saveLastAttemptTimestamp(timestamp: Long){
        userInfoDatastore.edit { preferences ->
            preferences[KEY_LAST_ATTEMPT_TIMESTAMP] = timestamp
        }
    }

    fun isFirstLaunch(): Flow<Boolean>{
        return userInfoDatastore.data.map { preferences ->
            preferences[KEY_IS_FIRST_LAUNCH] ?: true
        }
    }

    suspend fun saveFirstLaunch(){
        userInfoDatastore.edit { preferences ->
            preferences[KEY_IS_FIRST_LAUNCH] = false
        }
    }
}