package com.slvkdev.spellingchecker

import android.content.Context
import android.util.Log
import com.slvkdev.spellingchecker.database.DictionaryDatabaseManager

/*
    SpellingChecker is used to check if word exist or not
 */

class SpellingChecker(context: Context, dictionaryDatabaseAssetFilePath: String) {
    private val dbManager =
        DictionaryDatabaseManager.getInstance(context, dictionaryDatabaseAssetFilePath)

    suspend fun isWordExist(word: String): Boolean {
        val foundWord = dbManager.getWordOrNull(word)
        Log.d("SpellingChecker", "Checking for word: $word. Found: ${foundWord?.word}")
        return foundWord != null
    }
}