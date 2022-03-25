package com.slvkdev.wordle.common

import androidx.compose.ui.graphics.Color

data class IndicationColors(
    val correctLetterColor: Color = Color.Green,
    val existingLetterColor: Color = Color.Yellow,
    val wrongLetterColor: Color = Color.Gray,
    val uncheckedLetterColor: Color = Color.LightGray
) {
    fun getColorFor(keyState: LetterState): Color{
        return when(keyState){
            LetterState.Unchecked -> {
                uncheckedLetterColor
            }
            LetterState.Correct -> {
                correctLetterColor
            }
            LetterState.Exist -> {
                existingLetterColor
            }
            LetterState.Wrong -> {
                wrongLetterColor
            }
        }
    }
}