package com.slvkdev.ruwordle.data

import android.content.Context
import com.slvkdev.ruwordle.R
import com.slvkdev.ruwordle.utils.CalculateDailyWordId
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DailyWordRepository @Inject constructor(@ApplicationContext private val context: Context) {

    fun getDailyWord(): String {
        val words = context.resources.getStringArray(R.array.wordsToGuess)

        val dailyWordId = CalculateDailyWordId(
            currentTimestamp = System.currentTimeMillis(),
            maxId = words.lastIndex
        )
        return words[dailyWordId].lowercase()
    }
}