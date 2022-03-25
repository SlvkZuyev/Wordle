package com.slvkdev.wordle.keyboard.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.slvkdev.wordle.common.LetterState

class Key(val text: String = "", private var initialState: LetterState = LetterState.Unchecked) {
    var state: LetterState by mutableStateOf(initialState)
}
