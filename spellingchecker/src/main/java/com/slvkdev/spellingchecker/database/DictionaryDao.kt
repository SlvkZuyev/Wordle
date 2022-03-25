package com.slvkdev.spellingchecker.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.slvkdev.spellingchecker.model.Word

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary")
    suspend fun getAll(): List<Word>

    @Query("SELECT * FROM dictionary WHERE word = :word")
    suspend fun getIfExist(word: String): Word?

    @Insert
    suspend fun insertAll(vararg users: Word)

    @Delete
    suspend fun delete(user: Word)
}
