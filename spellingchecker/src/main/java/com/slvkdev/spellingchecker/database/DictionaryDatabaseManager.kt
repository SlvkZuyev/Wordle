package com.slvkdev.spellingchecker.database

import android.content.Context
import androidx.room.Room
import com.slvkdev.spellingchecker.model.Word


class DictionaryDatabaseManager private constructor(private val db: DictionaryDb) {
    suspend fun getWordIfExist(word: String): Word?{
        return db.userDao().getIfExist(word)
    }

    companion object {
        @Volatile
        private var INSTANCE: DictionaryDatabaseManager? = null

        @JvmStatic
        fun getInstance(context: Context, dictionaryDatabaseAssetFilePath: String): DictionaryDatabaseManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: run {
                    val db = Room.databaseBuilder(
                        context,
                        DictionaryDb::class.java, "Dictionary.db"
                    ).createFromAsset(dictionaryDatabaseAssetFilePath)
                        .build()
                    DictionaryDatabaseManager(db).also { INSTANCE = it }
                }
            }
        }
    }
}