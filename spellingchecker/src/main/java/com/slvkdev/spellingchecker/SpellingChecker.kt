package com.slvkdev.spellingchecker

import android.content.Context
import android.util.Log
import com.slvkdev.spellingchecker.database.DictionaryDatabaseManager

class SpellingChecker(context: Context, dictionaryDatabaseAssetFilePath: String) {
    private val dbManager = DictionaryDatabaseManager.getInstance(context, dictionaryDatabaseAssetFilePath)
    suspend fun isWordExist(word: String): Boolean{
        val foundWord = dbManager.getWordIfExist(word)
        Log.d("SpellingChecker", "Checking for word: $word  found: ${foundWord?.word}")
        return foundWord != null
    }
}