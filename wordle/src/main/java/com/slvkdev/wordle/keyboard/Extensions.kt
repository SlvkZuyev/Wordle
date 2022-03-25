package com.slvkdev.wordle.keyboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.slvkdev.wordle.common.LetterState
import com.slvkdev.wordle.keyboard.model.KeyboardDimensionsDp

@Composable
fun calculateWordleKeyboardDimensions() : KeyboardDimensionsDp{
    val keyPadding: PaddingValues = PaddingValues(horizontal = 1.dp, vertical = 2.dp)
    val keyboardWidth = LocalConfiguration.current.screenWidthDp.dp
    val keyWidth = keyboardWidth / 12
    val keyHeight = (keyWidth.value * 1.75).dp
    val specialKeyWidth = (keyHeight.value * 0.65).dp

    return KeyboardDimensionsDp(
        keyboardWidth = keyboardWidth,
        keyboardHeight = keyHeight * 3 + keyPadding.calculateTopPadding() * 3,
        keyWidth = keyWidth,
        keyHeight = keyHeight,
        specialKeyWidth = specialKeyWidth
    )
}

fun List<LetterState>.areAllCorrect(): Boolean{
    for(state in this){
        if(state != LetterState.Correct){
            return false
        }
    }
    return true
}