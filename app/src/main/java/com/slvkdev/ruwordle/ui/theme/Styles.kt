package com.slvkdev.ruwordle.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.slvkdev.wordle.common.IndicationColors
import com.slvkdev.wordle.field.models.WordleFieldStyle
import com.slvkdev.wordle.keyboard.model.KeyboardStyle

val indicationColors = IndicationColors(
    correctLetterColor = CorrectGreen,
    uncheckedLetterColor = Color.Transparent,
    existingLetterColor = ExistingYellow,
    wrongLetterColor = WrongGrey,
)

val wordleFieldStyle = WordleFieldStyle(
    letterStyle = TextStyle(
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    indicationColors = indicationColors,
    emptySquareBorderColor = LightGrey,
    letterRevealDuration = 650,
    delayBetweenLettersReveal = 300
)

val wordleKeyboardStyle = KeyboardStyle(
    indicationColors = indicationColors.copy(uncheckedLetterColor = Color.LightGray)
)