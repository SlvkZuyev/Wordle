package com.slvkdev.wordle.field.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.slvkdev.wordle.common.LetterState

class FieldSquare(initialLetter: String = "", initialState: LetterState = LetterState.Unchecked) {
    var letter: String by mutableStateOf(initialLetter)
    var state: LetterState by mutableStateOf(initialState)
}

fun List<FieldSquare>.areAllCorrect(): Boolean{
    for(square in this){
        if(square.state != LetterState.Correct){
            return false
        }
    }
    return true
}