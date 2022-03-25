package com.slvkdev.wordle.field

import com.slvkdev.wordle.common.LetterState
import com.slvkdev.wordle.snapshot_helper.WordleSnapshot
import com.slvkdev.wordle.field.models.FieldSquare
import com.slvkdev.wordle.utils.CompareWords
import com.slvkdev.wordle.field.utils.CreateEmptyGrid
import kotlinx.coroutines.delay


class WordleFieldGrid() {
    val grid = CreateEmptyGrid()
    var currentRowIndex = 0
    var currentColIndex = 0

    val nCols = 5
    val nRows = 6

    fun loadFromSnapshot(snapshot: WordleSnapshot) {
        for (word in snapshot.insertedWords) {
            for (letter in word) {
                addLetter(letter.toString())
            }
            val squareStates = CompareWords(word, snapshot.requestedWord)
            applyStatesToRow(squareStates)
            moveToNextRow()
        }
    }

    fun getInsertedWords(): List<String> {
        val words = mutableListOf<String>()
        for (row in grid) {
            var word = ""
            for (square in row) {
                word += square.letter
            }
            if (word.isNotEmpty()) {
                words.add(word)
            }
        }
        return words
    }

    fun moveToNextRow() {
        currentRowIndex++
        currentColIndex = 0
    }

    fun currentRowIsTheLast(): Boolean {
        return currentRowIndex >= nRows - 1
    }

    fun clearAll() {
        currentRowIndex = 0
        for (row in grid) {
            for (square in row) {
                square.state = LetterState.Unchecked
                square.letter = ""
            }
        }
    }

    fun hasEmptyRow(): Boolean {
        return currentRowIndex < nRows
    }

    fun addLetter(letter: String) {
        if (currentColIndex >= nCols || currentRowIndex >= nRows) {
            return
        }
        grid[currentRowIndex][currentColIndex].letter = letter.lowercase()
        currentColIndex++
    }

    fun removeLastLetter() {
        if (currentColIndex <= 0) {
            return
        }
        currentColIndex--
        grid[currentRowIndex][currentColIndex].letter = ""
    }

    fun getCurrentRow(): List<FieldSquare> {
        return grid[currentRowIndex]
    }

    fun getCurrentInput(): String {
        if (!hasEmptyRow()) {
            return ""
        }
        var word = ""
        for (square in grid[currentRowIndex]) {
            word += square.letter
        }
        return word
    }

    fun changeLetterState(letterIndex: Int, state: LetterState) {
        grid[currentRowIndex][letterIndex].state = state
    }

    suspend fun applyStatesToRowWithDelay(states: List<LetterState>, delay: Long) {
        val rowInd = currentRowIndex
        for (ind in 0 until nCols) {
            grid[rowInd][ind].state = states[ind]
            delay(delay)
        }
    }

    private fun applyStatesToRow(states: List<LetterState>) {
        for (ind in 0 until nCols) {
            grid[currentRowIndex][ind].state = states[ind]
        }
    }

}