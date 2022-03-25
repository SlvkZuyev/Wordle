package com.slvkdev.wordle.field.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.slvkdev.wordle.common.IndicationColors
import com.slvkdev.wordle.common.LetterState
import com.slvkdev.wordle.field.models.FieldSquare

class WordleFieldStyle(
    val letterStyle: TextStyle = TextStyle(fontSize = 24.sp),
    val indicationColors: IndicationColors = IndicationColors(),
    val emptySquareBorderColor: Color = Color.LightGray,
    val uncheckedLetterSquareBorderColor: Color = Color.Black,
    val letterRevealDuration : Int = 500,
    val delayBetweenLettersReveal : Long = 200L
){
    fun getBorderColorFor(square: FieldSquare): Color{
        if(square.letter == ""){
            return emptySquareBorderColor
        }
        if(square.state == LetterState.Unchecked){
            return uncheckedLetterSquareBorderColor
        }

        return indicationColors.getColorFor(square.state)
    }
}
