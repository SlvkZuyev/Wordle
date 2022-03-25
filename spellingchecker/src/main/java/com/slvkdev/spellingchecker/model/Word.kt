package com.slvkdev.spellingchecker.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "dictionary")
class Word {
    @NonNull
    @PrimaryKey
    var word: String = ""
}