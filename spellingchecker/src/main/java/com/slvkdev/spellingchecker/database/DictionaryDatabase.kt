package com.slvkdev.spellingchecker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.slvkdev.spellingchecker.model.Word

@Database(entities = [Word::class], version = 1, exportSchema = true)
abstract class DictionaryDb : RoomDatabase() {
    abstract fun userDao(): DictionaryDao
}

