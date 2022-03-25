package com.slvkdev.wordle.snapshot_helper

import com.slvkdev.wordle.keyboard.model.Key
import com.slvkdev.wordle.utils.CompareWords


class WordleSnapshot(
    var requestedWord: String = "",
    var insertedWords: List<String> = emptyList(),
    var timestamp: Long = System.currentTimeMillis()
) {
    fun convertToKeys(): List<Key> {
        val keys: MutableList<Key> = mutableListOf()
        for (word in insertedWords) {
            val states = CompareWords(word, requestedWord)
            for ((ind, letter) in word.withIndex()) {
                keys.add(Key(text = letter.toString(), initialState = states[ind]))
            }
        }
        return keys.toList()
    }
}